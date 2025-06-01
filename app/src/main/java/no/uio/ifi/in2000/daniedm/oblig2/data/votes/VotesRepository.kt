package no.uio.ifi.in2000.daniedm.oblig2.data.votes

import no.uio.ifi.in2000.daniedm.oblig2.domain.model.DistrictVotes
import javax.inject.Inject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async

class VotesRepository @Inject constructor(
    private val individualVotesDataSource: IndividualVotesDataSource,
    private val aggregatedVotesDataSource: AggregatedVotesDataSource
): ViewModel() {
    suspend fun getDistrictInfo(): Result<List<DistrictVotes>> {
        return try {
            val deferred1And2 = viewModelScope.async() {
                individualVotesDataSource.getDistrict1And2Votes()
            }

            val deferred3 = viewModelScope.async() {
                aggregatedVotesDataSource.getDistrict3Votes()
            }

            val result1And2 = deferred1And2.await()
            val result3 = deferred3.await()

            val combined = result1And2.getOrThrow() + result3.getOrThrow()
            Result.success(combined)

        } catch (e: Exception) {
            Result.failure(e)
        }


    }
}
