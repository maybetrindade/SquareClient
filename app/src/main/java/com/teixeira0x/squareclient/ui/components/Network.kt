package com.teixeira0x.squareclient.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.teixeira0x.squareclient.Strings

@Composable
fun NetworkError(
  modifier: Modifier = Modifier.fillMaxSize(),
  errorMessage: String,
  onRetry: (() -> Unit)? = null,
) {
  Column(
    modifier = modifier,
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Row(
      horizontalArrangement = Arrangement.Center,
      verticalAlignment = Alignment.CenterVertically,
    ) {
      Icon(
        imageVector = Icons.Outlined.Info,
        contentDescription = "info",
        modifier = Modifier.width(25.dp),
        tint = MaterialTheme.colorScheme.error,
      )
      Text(
        text = errorMessage,
        color = MaterialTheme.colorScheme.error,
        style = MaterialTheme.typography.bodySmall,
      )
    }

    if (onRetry != null) { // Retry button
      OutlinedButton(
        onClick = { onRetry.invoke() },
        border =
          BorderStroke(width = 2.dp, color = MaterialTheme.colorScheme.error),
      ) {
        Text(
          text = stringResource(Strings.common_retry),
          color = MaterialTheme.colorScheme.error,
          style = MaterialTheme.typography.bodySmall,
        )
      }
    }
  }
}
