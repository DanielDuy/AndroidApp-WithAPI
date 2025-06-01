package no.uio.ifi.in2000.daniedm.oblig2.data.votes

import no.uio.ifi.in2000.daniedm.oblig2.data.remote.District1Api
import no.uio.ifi.in2000.daniedm.oblig2.data.remote.District2Api
import no.uio.ifi.in2000.daniedm.oblig2.domain.model.District
import no.uio.ifi.in2000.daniedm.oblig2.domain.model.DistrictVotes
import javax.inject.Inject

class IndividualVotesDataSource @Inject constructor(
    private val district1Api: District1Api,
    private val district2Api: District2Api
) {
    suspend fun getDistrict1And2Votes(): Result<List<DistrictVotes>> {
        try {
            val serverResponseDistrict1 = district1Api.getDataFromDistrict1()
            val voteCounts = serverResponseDistrict1
                .groupBy { it.id }
                .map {(id, votes) ->
                    DistrictVotes(
                        district = District.DISTRICT1,
                        alpacaPartyId = id,
                        numberOfVotesForParty = votes.size
                    )
                }

            val serverResponseDistrict2 = district2Api.getDataFromDistrict2()
            val voteCounts2 = serverResponseDistrict2
                .groupBy { it.id }
                .map {(id, votes) ->
                    DistrictVotes(
                        district = District.DISTRICT2,
                        alpacaPartyId = id,
                        numberOfVotesForParty = votes.size
                    )
                }


            return Result.success(voteCounts + voteCounts2)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }
}