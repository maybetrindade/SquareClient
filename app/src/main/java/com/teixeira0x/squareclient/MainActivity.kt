package com.teixeira0x.squareclient

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.teixeira0x.squareclient.nav.accountDetailScreenRoute
import com.teixeira0x.squareclient.nav.accountTokenScreenRoute
import com.teixeira0x.squareclient.ui.screens.account.detail.AccountDetailScreen
import com.teixeira0x.squareclient.ui.screens.account.token.AccountTokenScreen
import com.teixeira0x.squareclient.ui.theme.SquareClientAppTheme
import dagger.hilt.android.AndroidEntryPoint

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
          startDestination = accountTokenScreenRoute(),
        ) {
          composable(accountTokenScreenRoute()) { AccountTokenScreen(navController) }

          composable(accountDetailScreenRoute()) { backStackEntry ->
            AccountDetailScreen(
              navController,
              backStackEntry.arguments?.getString("token") ?: "",
            )
          }
        }
      }
    }
  }
}
