package com.teixeira0x.squareclient.ui.screens.account.token

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.teixeira0x.squareclient.Strings
import com.teixeira0x.squareclient.data.validator.account.TokenValidator.isValidToken
import com.teixeira0x.squareclient.nav.accountDetailScreenRoute
import com.teixeira0x.squareclient.ui.components.Screen
import com.teixeira0x.squareclient.ui.components.TextField
import com.teixeira0x.squareclient.ui.components.TopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountTokenScreen(navController: NavController) {
  val viewModel = hiltViewModel<AccountTokenViewModel>()
  val isLoading = viewModel.isLoading
  val state = viewModel.state
  val token = state.token

  var isValidToken by remember { mutableStateOf(token.isValidToken()) }

  Screen(topBar = { TopBar(title = stringResource(Strings.token_title)) }) {
    paddingValues ->
    Column(
      modifier = Modifier.padding(paddingValues).fillMaxSize().padding(16.dp)
    ) {
      Text(
        text = stringResource(Strings.token_desc),
        style = MaterialTheme.typography.bodyMedium,
      )

      Spacer(modifier = Modifier.height(10.dp))

      TextField(
        modifier = Modifier.fillMaxWidth(),
        label = stringResource(Strings.token_field_hint),
        isError = !isValidToken,
        value = token,
      ) {
        viewModel.updateToken(it)
        isValidToken = it.isValidToken()
      }

      Spacer(modifier = Modifier.height(10.dp))

      Button(
        onClick = {
          if (!isLoading) {
            viewModel.fetchAccount(
              token = token.trim(),
              onSuccess = {
                navController.navigate(accountDetailScreenRoute(token))
              },
              onError = {},
            )
          }
        },
        modifier = Modifier.fillMaxWidth(),
        enabled = isValidToken,
      ) {
        if (isLoading) {
          CircularProgressIndicator(
            modifier = Modifier.width(15.dp).height(15.dp),
            color = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
          )
        } else Text(stringResource(Strings.token_btn_enter), maxLines = 1)
      }

      Spacer(modifier = Modifier.height(10.dp))

      viewModel.error?.let {
        Text(
          text = it,
          color = MaterialTheme.colorScheme.error,
          style = MaterialTheme.typography.bodySmall,
        )
      }
    }
  }
}
