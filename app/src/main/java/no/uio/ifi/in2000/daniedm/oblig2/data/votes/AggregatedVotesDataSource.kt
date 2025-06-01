package no.uio.ifi.in2000.daniedm.oblig2.data.votes

import no.uio.ifi.in2000.daniedm.oblig2.data.remote.District3Api
import no.uio.ifi.in2000.daniedm.oblig2.domain.model.District
import no.uio.ifi.in2000.daniedm.oblig2.domain.model.DistrictVotes
import javax.inject.Inject

class AggregatedVotesDataSource @Inject constructor(
    private val district3Api: District3Api
) {
    suspend fun getDistrict3Votes(): Result<List<DistrictVotes>> {
        try {
            val serverResponseDistrict3 = district3Api.getDataFromDistrict3().parties
            val votecounts3 = serverResponseDistrict3.map { aggregatedVotes ->
                DistrictVotes(
                    district = District.DISTRICT3,
                    alpacaPartyId = aggregatedVotes.partyId,
                    numberOfVotesForParty = aggregatedVotes.votes
                )
            }
            return Result.success(votecounts3)
        } catch  (e: Exception) {
            return Result.failure(e)
        }
    }
}
