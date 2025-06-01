package no.uio.ifi.in2000.daniedm.oblig2.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class DistrictVotes(
    val district: District,
    val alpacaPartyId: String,
    val numberOfVotesForParty: Int
)