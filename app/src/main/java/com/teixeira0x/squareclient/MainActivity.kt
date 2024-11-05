package com.teixeira0x.squareclient

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.teixeira0x.squareclient.nav.AccountDetailScreenRoute
import com.teixeira0x.squareclient.nav.TokenScreenRoute
import com.teixeira0x.squareclient.ui.screens.account.detail.AccountDetailScreen
import com.teixeira0x.squareclient.ui.screens.account.token.TokenScreen
import com.teixeira0x.squareclient.ui.theme.SquareClientAppTheme
import dagger.hilt.android.AndroidEntryPoint

/** @author Felipe Teixeira */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      SquareClientAppTheme {
        val navController = rememberNavController()

        NavHost(
          navController = navController,
          startDestination = TokenScreenRoute,
        ) {
          composable<TokenScreenRoute> { TokenScreen(navController) }

          composable<AccountDetailScreenRoute> { route ->
            AccountDetailScreen(/* ... */ )
          }
        }
      }
    }
  }
}
