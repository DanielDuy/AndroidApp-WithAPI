package no.uio.ifi.in2000.daniedm.oblig2.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import no.uio.ifi.in2000.daniedm.oblig2.domain.model.DistrictVotes

@Composable
fun VoteList(districtVotes: DistrictVotes) {
    Column {
        Text("Party: "+districtVotes.alpacaPartyId + ", Votes: "+districtVotes.numberOfVotesForParty.toString())
    }
}