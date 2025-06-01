package no.uio.ifi.in2000.daniedm.oblig2.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class PartyInfo (
    val id: Int,
    val name: String,
    val leader: String,
    val img: String,
    val color: String,
    val description: String
)