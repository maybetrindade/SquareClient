package com.teixeira0x.squareclient.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screen(
  topBar: @Composable () -> Unit,
  content: @Composable (PaddingValues) -> Unit,
) {
  Scaffold(topBar = topBar, content = content)
}
