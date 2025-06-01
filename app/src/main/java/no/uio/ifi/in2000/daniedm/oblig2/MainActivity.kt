package no.uio.ifi.in2000.daniedm.oblig2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import no.uio.ifi.in2000.daniedm.oblig2.navigation.MainNavHost
import no.uio.ifi.in2000.daniedm.oblig2.ui.theme.Daniedm_oblig2Theme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Daniedm_oblig2Theme {
                MainNavHost(modifier = Modifier)
            }
        }
    }
}
