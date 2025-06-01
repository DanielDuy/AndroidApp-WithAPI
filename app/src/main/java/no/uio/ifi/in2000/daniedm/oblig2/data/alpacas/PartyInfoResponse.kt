package no.uio.ifi.in2000.daniedm.oblig2.data.alpacas

import kotlinx.serialization.Serializable
import no.uio.ifi.in2000.daniedm.oblig2.domain.model.PartyInfo

@Serializable
data class PartyInfoResponse(
    val parties: List<PartyInfo>
)
