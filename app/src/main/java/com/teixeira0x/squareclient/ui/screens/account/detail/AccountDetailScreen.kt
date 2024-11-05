package com.teixeira0x.squareclient.ui.screens.account.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.teixeira0x.squareclient.ui.components.Screen
import com.teixeira0x.squareclient.ui.components.TopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountDetailScreen() {

  val viewModel = hiltViewModel<DetailViewModel>()

  Screen(topBar = { TopBar(title = "User Screen") }) { paddingValues ->
    Column(modifier = Modifier.padding(paddingValues).fillMaxSize()) {
      Text(":)")
    }
  }
}
