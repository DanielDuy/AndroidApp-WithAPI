package no.uio.ifi.in2000.daniedm.oblig2.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale.Companion.FillWidth
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import no.uio.ifi.in2000.daniedm.oblig2.domain.model.PartyInfo

@Composable
fun PartyCard(
    partyInfo: PartyInfo,
    navigateToParty: (Int) -> Unit
) {
    Box(
        modifier = Modifier
            .height(140.dp)
            .background(Color(android.graphics.Color.parseColor(partyInfo.color)), shape = RoundedCornerShape(10.dp))
            .clickable { navigateToParty(partyInfo.id) }
    ) {
        Column(
            modifier = Modifier
                .background(Color.Gray, shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
                .padding(10.dp)
                .width(150.dp)
                .height(110.dp)
                .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 16.dp)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(partyInfo.name)
            AsyncImage(
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape),
                model = partyInfo.img,
                contentDescription = null,
                contentScale = FillWidth
            )
            Text("Leder: "+partyInfo.leader)

        }
    }
}

@Preview
@Composable
fun PartyCardPreview() {
//    val partyInfo =
//        PartyInfo(id = 0, name = "Name", leader = "Leader", img = "", color = "", description = "")
//    PartyCard(partyInfo, {})


}