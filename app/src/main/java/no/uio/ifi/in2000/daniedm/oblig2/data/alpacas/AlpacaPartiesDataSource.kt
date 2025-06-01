package no.uio.ifi.in2000.daniedm.oblig2.data.alpacas

import no.uio.ifi.in2000.daniedm.oblig2.data.remote.PartyInfoApi
import no.uio.ifi.in2000.daniedm.oblig2.domain.model.PartyInfo
import javax.inject.Inject

class AlpacaPartiesDataSource @Inject constructor(
    private val partyInfoApi: PartyInfoApi
) {

    suspend fun getAlpacaParties(): Result<List<PartyInfo>> {
        return try {
            val serverResponse = partyInfoApi.getAlpacaParties().parties
            Result.success(serverResponse)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}