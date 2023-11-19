package com.service.infrastructure.repository.creditcard.brand.diners

import com.service.core.domain.creditcard.CreditCardBrand
import com.service.infrastructure.repository.creditcard.BaseCreditCardRepository
import com.service.infrastructure.repository.creditcard.expiration.ExpirationDateGenerator
import com.service.infrastructure.repository.creditcard.number.CreditCardNumberBuilder

class DinersCreditCardRepository(
    creditCardNumberGenerator: CreditCardNumberBuilder,
    expirationDateGenerator: ExpirationDateGenerator,
): BaseCreditCardRepository(
    creditCardNumberGenerator,
    expirationDateGenerator,
    CreditCardBrand.DINERS
)