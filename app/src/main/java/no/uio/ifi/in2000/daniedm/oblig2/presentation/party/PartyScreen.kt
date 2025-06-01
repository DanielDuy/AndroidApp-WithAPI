package no.uio.ifi.in2000.daniedm.oblig2.presentation.party

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale.Companion.FillWidth
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage

@Composable
fun PartyScreen(
    navigateBack: () -> Unit
) {
    PartyScreenContent(hiltViewModel(), navigateBack)
}

@Composable
fun PartyScreenContent(
    viewModel: PartyViewModel,
    navigateBack: () -> Unit
) {
    val uiState by viewModel.uiState
    Column(
        modifier = Modifier.padding(vertical = 48.dp, horizontal = 16.dp)
    ) {
        IconButton(onClick = navigateBack) {
            Icon(
                Icons.AutoMirrored.Default.ArrowBack,
                contentDescription = "Navigate back"
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (uiState.selectedParty != null) {
                Text(uiState.selectedParty!!.name)
                AsyncImage(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape),
                    model = uiState.selectedParty!!.img,
                    contentDescription = null,
                    contentScale = FillWidth
                )
                Text("Leder: " + uiState.selectedParty!!.leader)
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp)
                        .background(Color(android.graphics.Color.parseColor(uiState.selectedParty!!.color))))
                Text(uiState.selectedParty!!.description)
            }
        }
    }
}