package com.teixeira0x.squareclient.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

@Composable
fun HideableText(
  text: String,
  fontSize: TextUnit = TextUnit.Unspecified,
  maxLines: Int = Int.MAX_VALUE,
  minLines: Int = 1,
  style: TextStyle = LocalTextStyle.current,
) {
  var isVisible by remember { mutableStateOf(false) }

  Row(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.Center,
    verticalAlignment = Alignment.CenterVertically,
  ) {
    Text(
      text =
        if (isVisible) text
        else
          "•".repeat(/* Don't put the same amount of letters in the text. */ 5),
      modifier = Modifier.fillMaxWidth().weight(1f),
      fontSize = fontSize,
      maxLines = maxLines,
      minLines = minLines,
      style = style,
    )
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
