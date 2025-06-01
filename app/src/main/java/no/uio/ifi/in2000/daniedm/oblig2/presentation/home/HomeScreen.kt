package no.uio.ifi.in2000.daniedm.oblig2.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import no.uio.ifi.in2000.daniedm.oblig2.domain.model.District

@Composable
fun HomeScreen(
    navigateToParty: (Int) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        HomeScreenContent(hiltViewModel(), navigateToParty)
    }
}

@Composable
fun HomeScreenContent(
    viewModel: HomeScreenViewModel,
    navigateToParty: (Int) -> Unit
) {
    val uiState by viewModel.uiState
    LazyColumn {
        item {
            Text(
                text = "Partier",
                fontSize = 20.sp,
                modifier = Modifier.padding(top = 30.dp)
            )
        }
        items(uiState.parties) { item ->
            PartyCard(
                partyInfo = item,
                navigateToParty = navigateToParty
            )
            Spacer(modifier = Modifier.height(10.dp))
        }
        item {
            uiState.votesByParty.forEach { (partyId, votes) ->
                Text(
                    text = "Party $partyId: $votes votes",
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }
        }
        item {
            Button(onClick = { viewModel.toggleDropDown(true) }) {
                Text(text = "Select District")
            }
            DropdownMenu(
                expanded = uiState.isExpanded,
                onDismissRequest = { viewModel.toggleDropDown(false) }
            ) {
                District.entries.forEach { item ->
                    DropdownMenuItem(
                        onClick = {
                            viewModel.updateSelectedItem(item)
                            viewModel.toggleDropDown(false)
                        },
                        text = { Text(text = item.name) }
                    )
                }
            }
        }
        items(uiState.filteredVotes) { item ->
            VoteList(districtVotes = item)
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}
