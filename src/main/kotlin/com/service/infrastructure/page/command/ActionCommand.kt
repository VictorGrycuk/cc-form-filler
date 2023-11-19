package com.service.infrastructure.page.command

import com.service.core.domain.creditcard.CreditCard

// ---
interface ActionCommand {
    fun execute(card: CreditCard): Boolean
}