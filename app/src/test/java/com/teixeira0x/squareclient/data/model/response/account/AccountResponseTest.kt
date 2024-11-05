package com.teixeira0x.squareclient.data.model.response.account

import com.google.gson.Gson
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

const val FAKE_SUCCESS_PAYLOAD =
  """
{
  "status": "success",
  "response": {
    "user": {
      "id": "313397145698959364",
      "name": "joaootavios",
      "email": "joao@squarecloud.app",
      "plan": {
        "name": "standard",
        "memory": {
          "limit": 2048,
          "available": 1536,
          "used": 256
        },
        "duration": 1732072955446
      }
    },
    "applications": [
      {
        "name": "Square Example APP",
        "id": "446b0b4118634a1c99e73bac9a54e475",
        "desc": "This is a example of description",
        "ram": 512,
        "lang": "javascript",
        "domain": "square-example-app.squareweb.app",
        "custom": "mydomain.com",
        "cluster": "fl-micron-1"
      }
    ]
  }
}
"""

const val FAKE_ERROR_PAYLOAD =
  """
{
  "status": "error",
  "code": "ACCESS_DENIED"
}
"""

/** @author Felipe Teixeira */
class AccountResponseTest {

  private val gson = Gson()

  @Test
  fun `test payload success parsing`() {
    val responsePayload =
      gson.fromJson(FAKE_SUCCESS_PAYLOAD, ResponsePayload::class.java)

    assertNotNull(responsePayload)
    assertEquals("success", responsePayload.status)
    assertNotNull(responsePayload.response)

    // Verify user data
    val user = responsePayload.response!!.user
    assertEquals("313397145698959364", user.id)
    assertEquals("joaootavios", user.name)
    assertEquals("joao@squarecloud.app", user.email)

    // Verify user plan data
    val plan = user.plan
    assertEquals("standard", plan.name)
    assertEquals(2048L, plan.memory.limit)
    assertEquals(1536L, plan.memory.available)
    assertEquals(256L, plan.memory.used)
    assertEquals(1732072955446, plan.duration)

    // Verify account applications
    val applications = responsePayload.response.applications
    assertEquals(1, applications.size)
    val application = applications[0]
    assertEquals("Square Example APP", application.name)
    assertEquals("446b0b4118634a1c99e73bac9a54e475", application.id)
    assertEquals("This is a example of description", application.desc)
    assertEquals(512L, application.ram)
    assertEquals("javascript", application.lang)
    assertEquals("square-example-app.squareweb.app", application.domain)
    assertEquals("mydomain.com", application.custom)
    assertEquals("fl-micron-1", application.cluster)
  }

  @Test
  fun `test payload error parsing`() {
    val responsePayload =
      gson.fromJson(FAKE_ERROR_PAYLOAD, ResponsePayload::class.java)

    assertNotNull(responsePayload)
    assertEquals("error", responsePayload.status)
    assertNotNull(responsePayload.code)
  }
}
