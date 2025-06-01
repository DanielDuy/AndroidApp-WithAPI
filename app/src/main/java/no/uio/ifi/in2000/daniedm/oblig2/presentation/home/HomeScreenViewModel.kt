package no.uio.ifi.in2000.daniedm.oblig2.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import no.uio.ifi.in2000.daniedm.oblig2.data.alpacas.AlpacaPartiesRepository
import no.uio.ifi.in2000.daniedm.oblig2.data.votes.VotesRepository
import no.uio.ifi.in2000.daniedm.oblig2.domain.model.District
import no.uio.ifi.in2000.daniedm.oblig2.domain.model.DistrictVotes
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repository: AlpacaPartiesRepository,
    private val repository2: VotesRepository

) : ViewModel() {


    private val _uiState = mutableStateOf(HomeScreenUiState())
    val uiState: State<HomeScreenUiState> = _uiState

    init {
        getPartyInfoList()
        getDistrictInfoList()
    }

    private fun getPartyInfoList() {
        _uiState.value = _uiState.value.copy(isLoading = true)
        viewModelScope.launch {
            val result = repository.getPartyInfos()
            result.fold(
                onSuccess = { info ->
                    _uiState.value = _uiState.value.copy(parties = info, isLoading = false)
                },
                onFailure = { exception -> _uiState.value = _uiState.value.copy(isLoading = false) }
            )
        }
    }

    private fun getDistrictInfoList() {
        _uiState.value = _uiState.value.copy(isLoading = true)
        viewModelScope.launch {
            val result = repository2.getDistrictInfo()
            result.fold(
                onSuccess = { info ->
                    val votesByParty = calulateVotesByParty(info)
                    _uiState.value = _uiState.value.copy(votes = info, isLoading = false, votesByParty = votesByParty)

                },
                onFailure = { exception -> _uiState.value = _uiState.value.copy(isLoading = false) }
            )
        }
    }

    private fun calulateVotesByParty(votes: List<DistrictVotes>): Map<String, Int> {
        return votes
            .groupBy { it.alpacaPartyId }
            .mapValues { (_, group) -> group.sumOf { it.numberOfVotesForParty } }
    }

    fun updateSelectedItem(selectedItem: District) {
        _uiState.value = _uiState.value.copy(selectedItem = selectedItem)
        getVotesByDistrict(selectedItem)
    }

    fun toggleDropDown(expanded: Boolean) {
        _uiState.value = _uiState.value.copy(isExpanded = expanded)
    }

    fun getVotesByDistrict(distict: District) {
        val filteredVotes = _uiState.value.votes.filter { it.district == distict }
        _uiState.value = _uiState.value.copy(filteredVotes = filteredVotes)
    }
}