package com.teixeira0x.squareclient.domain.repository

import com.teixeira0x.squareclient.domain.model.service.ServiceStatus

interface ServiceRepository {

  fun getStatus(): Result<ServiceStatus>
}
