package no.uio.ifi.in2000.daniedm.oblig2.data.remote

import no.uio.ifi.in2000.daniedm.oblig2.data.votes.District3VotesResponse
import retrofit2.http.GET

interface District3Api {
    @GET("district3")
    suspend fun getDataFromDistrict3(): District3VotesResponse
}