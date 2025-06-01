package no.uio.ifi.in2000.daniedm.oblig2.data.alpacas

import no.uio.ifi.in2000.daniedm.oblig2.domain.model.PartyInfo
import javax.inject.Inject

class AlpacaPartiesRepository @Inject constructor(
    private val alpacaPartiesDataSource: AlpacaPartiesDataSource
) {
    suspend fun getPartyInfos(): Result<List<PartyInfo>> {
        return alpacaPartiesDataSource.getAlpacaParties()
    }

    suspend fun getSelectedParty(id: Int): PartyInfo? {
        return getPartyInfos().getOrNull()?.find { it.id == id }
    }
}