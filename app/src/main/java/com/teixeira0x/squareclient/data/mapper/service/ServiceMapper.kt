package com.teixeira0x.squareclient.data.mapper.service

import com.teixeira0x.squareclient.data.model.response.service.ServiceStatusResponse
import com.teixeira0x.squareclient.domain.model.service.ServiceStatus

/**
 * Class with useful functions for mapping response models to domain models.
 *
 * @author Felipe Teixeira
 */
object ServiceMapper {

  fun ServiceStatusResponse.toServiceStatus(): ServiceStatus {
    return ServiceStatus(status = status, message = message)
  }
}
