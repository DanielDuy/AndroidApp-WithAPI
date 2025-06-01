package no.uio.ifi.in2000.daniedm.oblig2.data.votes

import kotlinx.serialization.Serializable
import no.uio.ifi.in2000.daniedm.oblig2.domain.model.AggregatedVotes

@Serializable
data class District3VotesResponse(
    val parties: List<AggregatedVotes>
)

