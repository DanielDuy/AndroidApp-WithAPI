package no.uio.ifi.in2000.daniedm.oblig2.presentation.party

import no.uio.ifi.in2000.daniedm.oblig2.domain.model.PartyInfo


data class PartyScreenUiState(
    val partyId: Int? = null,
    val selectedParty: PartyInfo? = null
)