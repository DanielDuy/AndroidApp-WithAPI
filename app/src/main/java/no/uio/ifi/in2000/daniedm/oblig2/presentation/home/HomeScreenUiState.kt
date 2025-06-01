package no.uio.ifi.in2000.daniedm.oblig2.presentation.home

import no.uio.ifi.in2000.daniedm.oblig2.domain.model.District
import no.uio.ifi.in2000.daniedm.oblig2.domain.model.DistrictVotes
import no.uio.ifi.in2000.daniedm.oblig2.domain.model.PartyInfo

data class HomeScreenUiState(
    val isLoading:Boolean = false,
    val parties:List<PartyInfo> = emptyList(),
    val votes: List<DistrictVotes> = emptyList(),
    val currentParty:PartyInfo? = null,
    val votesByParty: Map<String, Int> = emptyMap(),
    val selectedItem: District = District.DISTRICT1,
    val isExpanded: Boolean = false,
    val filteredVotes: List<DistrictVotes> = emptyList()
)
