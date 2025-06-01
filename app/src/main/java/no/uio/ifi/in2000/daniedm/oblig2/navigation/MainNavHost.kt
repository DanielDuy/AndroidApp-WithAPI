package no.uio.ifi.in2000.daniedm.oblig2.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import no.uio.ifi.in2000.daniedm.oblig2.presentation.home.HomeScreen
import no.uio.ifi.in2000.daniedm.oblig2.presentation.party.PartyScreen


@Composable
fun MainNavHost(
    modifier: Modifier
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        modifier = modifier,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                navigateToParty = { partyId -> navController.navigate("${Screen.Party.route}/$partyId") }
            )
        }
        composable(
            route = "${Screen.Party.route}/{partyId}",
            arguments = listOf(navArgument("partyId") { type = NavType.IntType })
        ) {
            PartyScreen(
                navigateBack = { navController.navigateUp() }
            )
        }
    }
}

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Party : Screen("party")
}