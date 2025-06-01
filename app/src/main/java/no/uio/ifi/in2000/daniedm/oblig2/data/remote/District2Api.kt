package no.uio.ifi.in2000.daniedm.oblig2.data.remote

import no.uio.ifi.in2000.daniedm.oblig2.domain.model.Vote
import retrofit2.http.GET

interface District2Api {
    @GET("district2")
    suspend fun getDataFromDistrict2(): List<Vote>

}