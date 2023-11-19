package com.service.core.domain.creditcard

data class CardHolder(
    val firstName: String,
    val middleName: String?,
    val lastName: String
)  {
    fun getFullName() = "${ getName() } $lastName"

    fun getName() = "$firstName $middleName".replace(" null", "")
}