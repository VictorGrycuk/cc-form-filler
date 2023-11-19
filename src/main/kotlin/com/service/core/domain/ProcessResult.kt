package com.service.core.domain

import com.service.core.domain.creditcard.CreditCard

data class ProcessResult(
    val card: CreditCard,
    val success: Boolean,
)