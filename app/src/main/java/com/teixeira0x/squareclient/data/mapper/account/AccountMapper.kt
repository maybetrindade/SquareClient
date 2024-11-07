package com.teixeira0x.squareclient.data.mapper.account

import com.teixeira0x.squareclient.data.model.response.account.*
import com.teixeira0x.squareclient.domain.model.Account
import com.teixeira0x.squareclient.domain.model.Application
import com.teixeira0x.squareclient.domain.model.Memory
import com.teixeira0x.squareclient.domain.model.Plan
import com.teixeira0x.squareclient.domain.model.User

/**
 * Class with useful functions to convert account response objects [data.model.response.account] into domain models [domain.model].
 *
 * @author Felipe Teixeira
 */
object AccountMapper {

  fun AccountResponse.toAccount(): Account {
    return Account(
      user = user.toUser(),
      applications = applications.toApplications(),
    )
  }

  fun UserResponse.toUser(): User {
    return User(id = id, name = name, email = email, plan = plan.toPlan())
  }

  fun PlanResponse.toPlan(): Plan {
    return Plan(name = name, memory = memory.toMemory(), duration = duration)
  }

  fun MemoryResponse.toMemory(): Memory {
    return Memory(limit = limit, available = available, used = used)
  }

  fun ApplicationResponse.toApplication(): Application {
    return Application(
      id = id,
      name = name,
      desc = desc,
      ram = ram,
      lang = lang,
      domain = domain,
      custom = custom,
      cluster = cluster,
    )
  }

  fun List<ApplicationResponse>.toApplications(): List<Application> {
    return map { it.toApplication() }
  }
}
