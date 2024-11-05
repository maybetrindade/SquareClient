package com.teixeira0x.squareclient.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextField(
  modifier: Modifier = Modifier,
  shape: Shape = RoundedCornerShape(8.dp),
  label: String,
  value: String,
  isError: Boolean = false,
  maxLines: Int = 1,
  minLines: Int = 1,
  onValueChange: (String) -> Unit = {},
) {
  OutlinedTextField(
    value = value,
    onValueChange = onValueChange,
    modifier = modifier,
    label = { Text(text = label, maxLines = 1) },
    maxLines = maxLines,
    minLines = minLines,
    isError = isError,
    shape = shape,
  )
}
