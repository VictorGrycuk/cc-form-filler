package com.service.infrastructure.repository.creditcard.holder

import com.service.core.domain.creditcard.CardHolder
import io.bloco.faker.Faker

class HolderGenerator {
    fun getCardHolder(): CardHolder {
        val faker = Faker(Locale.values().random().value)

        val firstName = faker.name.firstName()

        // Lets create a middle name 50% of the time
        val middleName = if(faker.bool.bool(0.5f)) faker.name.firstName() else null

        // Lets create a double last name 10% of the time
        val lastName = if(faker.bool.bool(0.9f)) faker.name.lastName() else "${faker.name.lastName()} ${faker.name.lastName()}"

        return CardHolder(
            firstName = firstName,
            middleName = middleName,
            lastName = lastName,
        )
    }
}