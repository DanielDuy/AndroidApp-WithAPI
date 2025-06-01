package no.uio.ifi.in2000.daniedm.oblig2.data.remote

import no.uio.ifi.in2000.daniedm.oblig2.domain.model.Vote
import retrofit2.http.GET

interface District1Api {
    @GET("district1")
    suspend fun getDataFromDistrict1(): List<Vote>
}