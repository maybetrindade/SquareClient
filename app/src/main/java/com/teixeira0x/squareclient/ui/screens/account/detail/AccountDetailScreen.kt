package com.teixeira0x.squareclient.ui.screens.account.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.teixeira0x.squareclient.Strings
import com.teixeira0x.squareclient.domain.model.account.Account
import com.teixeira0x.squareclient.ui.components.Screen
import com.teixeira0x.squareclient.ui.components.TopBar
import java.text.DateFormat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountDetailScreen(navController: NavController, token: String) {
  val viewModel = hiltViewModel<AccountDetailViewModel>()
  val isLoading = viewModel.isLoading
  val errorMessage = viewModel.error
  val state = viewModel.state
  val account = state.account

  LaunchedEffect(token) { viewModel.fetchAccount(token) }

  Screen(
    topBar = { TopBar(title = stringResource(Strings.account_detail_title)) }
  ) { paddingValues ->
    Column(
      modifier = Modifier.padding(paddingValues).fillMaxSize().padding(8.dp)
    ) {
      when {
        isLoading -> AccountLoading()
        else -> AccountDetailCard(account, errorMessage)
      }
    }
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AccountDetailCard(account: Account?, errorMessage: String?) =
  OutlinedCard(modifier = Modifier.fillMaxWidth()) {
    Column(modifier = Modifier.padding(10.dp).fillMaxWidth()) {
      if (account == null) {
        SimpleError(errorMessage ?: stringResource(Strings.account_fetch_error))
        return@Column
      }

      val user = account.user
      val plan = user.plan

      // User details
      AccountDetailItem(
        name = stringResource(Strings.account_detail_user_username),
        value = user.name,
      )
      HideableAccountDetailItem(
        name = stringResource(Strings.account_detail_user_email),
        value = user.email,
      )
      AccountDetailItem(
        name = stringResource(Strings.account_detail_user_id),
        value = user.id,
      )

      // User plan details
      AccountDetailItem(
        name = stringResource(Strings.account_detail_user_plan),
        value = plan.name,
      )
      AccountDetailItem(
        name = stringResource(Strings.account_detail_user_plan_expiration),
        value =
          plan.duration?.let { formatTime(it) }
            ?: stringResource(Strings.common_unavailable),
      )
      AccountDetailItem(
        name = stringResource(Strings.account_detail_user_plan_memory),
        value = plan.memory.available.toString(),
      )
    }
  }

@Composable
private fun AccountDetailItem(name: String, value: String) =
  Column(modifier = Modifier.padding(8.dp).fillMaxWidth()) {
    Text(text = name, maxLines = 1, style = MaterialTheme.typography.titleSmall)
    Spacer(modifier = Modifier.height(1.dp))
    Text(text = value, maxLines = 1, style = MaterialTheme.typography.bodyLarge)
  }

@Composable
private fun HideableAccountDetailItem(
  name: String,
  value: String,
  visible: Boolean = false,
) {
  var isVisible by remember { mutableStateOf(visible) }

  Row(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.Center,
    verticalAlignment = Alignment.CenterVertically,
  ) {
    Column(modifier = Modifier.fillMaxWidth().weight(1f)) {
      AccountDetailItem(
        name,
        if (isVisible) value
        else "•".repeat(/* Do not use the original value length. */ 5),
      )
    }

    IconButton(onClick = { isVisible = !isVisible }) {
      Icon(
        modifier = Modifier.width(15.dp),
        imageVector =
          if (isVisible) Icons.Default.VisibilityOff
          else Icons.Default.Visibility,
        contentDescription = if (isVisible) "hide" else "show",
      )
    }
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AccountLoading() =
  Column(
    modifier = Modifier.padding(10.dp).fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    CircularProgressIndicator(
      modifier = Modifier.width(50.dp),
      color = MaterialTheme.colorScheme.secondary,
      trackColor = MaterialTheme.colorScheme.surfaceVariant,
    )
  }

/** I don't know how to make a nice UI to handle errors. */
@Composable
private fun SimpleError(errorMessage: String) =
  Column(
    modifier = Modifier.padding(10.dp).fillMaxWidth(),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Text(text = errorMessage, style = MaterialTheme.typography.bodyMedium)
  }

private fun formatTime(time: Long): String {
  return DateFormat.getDateTimeInstance().format(time)
}
