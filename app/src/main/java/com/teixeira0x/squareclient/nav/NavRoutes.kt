package com.teixeira0x.squareclient.nav

/** Here are screen navigation routes. */

/** Navigation route to the token insertion screen */
fun accountTokenScreenRoute() = "account_token_screen"

/**
 * Navigation route to account details screen obtained with token.
 *
 * @param token The account token for the details or by default the declaration
 *   of an argument to the `{token}` route.
 */
fun accountDetailScreenRoute(token: String = "{token}") =
  "account_detail_screen/$token"
