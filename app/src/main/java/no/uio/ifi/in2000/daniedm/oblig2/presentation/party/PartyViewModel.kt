package no.uio.ifi.in2000.daniedm.oblig2.presentation.party

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import no.uio.ifi.in2000.daniedm.oblig2.data.alpacas.AlpacaPartiesRepository
import javax.inject.Inject

@HiltViewModel
class PartyViewModel @Inject constructor(
    private val repository: AlpacaPartiesRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val partyId: Int? = savedStateHandle.get<Int>("partyId")

    private val _uiState = mutableStateOf(PartyScreenUiState())
    val uiState: State<PartyScreenUiState> = _uiState

    init {
        getPartyDetails()
    }

    private fun getPartyDetails() {
        _uiState.value = _uiState.value.copy(partyId = partyId)
        if (partyId != null) {
            viewModelScope.launch {
                val party = repository.getSelectedParty(partyId)
                _uiState.value = _uiState.value.copy(selectedParty = party)
            }
        }
    }
}