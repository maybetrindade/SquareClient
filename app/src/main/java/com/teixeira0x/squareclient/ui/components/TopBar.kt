package com.teixeira0x.squareclient.ui.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
  title: String,
  actions: @Composable RowScope.() -> Unit = {},
  scrollBehavior: TopAppBarScrollBehavior? = null,
  onClickBackButton: (() -> Unit)? = null,
) {
  TopAppBar(
    title = { Text(text = title) },
    navigationIcon = {
      if (onClickBackButton != null) {
        IconButton(onClickBackButton) {
          Icon(
            imageVector = Icons.AutoMirrored.Default.ArrowBack,
            contentDescription = "Back",
          )
        }
      }
    },
    scrollBehavior = scrollBehavior,
    actions = actions,
  )
}
