package com.service.infrastructure.repository.creditcard.brand.discover

import com.service.core.domain.creditcard.CreditCardBrand
import com.service.infrastructure.repository.creditcard.BaseCreditCardRepository
import com.service.infrastructure.repository.creditcard.expiration.ExpirationDateGenerator
import com.service.infrastructure.repository.creditcard.number.CreditCardNumberBuilder

class DiscoverCreditCardRepository(
    creditCardNumberBuilder: CreditCardNumberBuilder,
    expirationDateGenerator: ExpirationDateGenerator,
): BaseCreditCardRepository(
    creditCardNumberBuilder,
    expirationDateGenerator,
    CreditCardBrand.DISCOVER,
)