package com.service.core.domain.creditcard

data class CreditCardExpiration(
    val month: Int,
    val year: Int,
) {
    override fun toString() = "${month.toString().padStart(2, '0')}/$year"
    fun getYear() = year.toString().padStart(2, '0')
    fun getMonth() = month.toString().padStart(2, '0')
}