package com.service.infrastructure.repository.creditcard.brand.visa

import com.service.core.domain.creditcard.CreditCardBrand
import com.service.infrastructure.repository.creditcard.BaseCreditCardRepository
import com.service.infrastructure.repository.creditcard.expiration.ExpirationDateGenerator
import com.service.infrastructure.repository.creditcard.number.CreditCardNumberBuilder

class VisaCreditCardRepository(
    creditCardNumberBuilder: CreditCardNumberBuilder,
    expirationDateGenerator: ExpirationDateGenerator,
): BaseCreditCardRepository(
    creditCardNumberBuilder,
    expirationDateGenerator,
    CreditCardBrand.VISA
)

