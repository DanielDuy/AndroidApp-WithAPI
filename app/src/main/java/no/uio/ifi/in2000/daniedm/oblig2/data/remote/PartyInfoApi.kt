package no.uio.ifi.in2000.daniedm.oblig2.data.remote


import no.uio.ifi.in2000.daniedm.oblig2.data.alpacas.PartyInfoResponse
import retrofit2.http.GET

interface PartyInfoApi {

    @GET("alpacaparties")
    suspend fun getAlpacaParties(): PartyInfoResponse

}