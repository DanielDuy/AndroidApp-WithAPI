package no.uio.ifi.in2000.daniedm.oblig2.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class AggregatedVotes(
    val partyId: String,
    val votes: Int
)
