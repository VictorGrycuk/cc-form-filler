package com.service.infrastructure.page.fakecorreoargentino

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import com.service.core.domain.creditcard.CreditCard
import com.service.core.domain.creditcard.CreditCardBrand
import com.service.infrastructure.Repositories
import com.service.infrastructure.repository.creditcard.CreditCardRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FakeCorreoArgentinoServiceTest {
    private val mockedRepository = Mockito.mock(CreditCardRepository::class.java)
    private val mockedPage = Mockito.mock(FakeCorreoArgentinoPage::class.java)
    private val correoArgentinoService = FakeCorreoArgentinoService(mockedRepository, mockedPage)
    private lateinit var creditCard: CreditCard

    @AfterEach
    fun tearDown() = Mockito.reset(mockedPage)

    @Test
    fun `should complete the form in the correct order`() {
        creditCard = Repositories.creditCardRepository.getCreditCard(CreditCardBrand.VISA)
        whenever(mockedRepository.getRandomCreditCard()).thenReturn(creditCard)

        correoArgentinoService.completeForm()

        val inOrder = Mockito.inOrder(mockedPage)
        inOrder.verify(mockedPage).navigateTo()
        inOrder.verify(mockedPage).fillCardNumber(creditCard.number)
        inOrder.verify(mockedPage).fillExpirationMonth(creditCard.expiration.getMonth())
        inOrder.verify(mockedPage).fillExpirationYear(creditCard.expiration.getYear())
        inOrder.verify(mockedPage).fillCVV(creditCard.cvv)
        inOrder.verify(mockedPage).clickAccept()
        inOrder.verify(mockedPage).waitForSMSInput()
    }

    @Test
    fun `should not complete CVV if the brand is either Diners or Amex`() {
        listOf(CreditCardBrand.DINERS, CreditCardBrand.AMEX).forEach { brand ->
            creditCard = Repositories.creditCardRepository.getCreditCard(brand)
            whenever(mockedRepository.getRandomCreditCard()).thenReturn(creditCard)

            correoArgentinoService.completeForm()

            Mockito.inOrder(mockedPage)
            verify(mockedPage, never()).fillCVV(anyString())
        }
    }

    @Test
    fun `should return a result after an unsuccessful form completion`() {
        whenever(mockedPage.fillCardNumber (anyString())).thenThrow(RuntimeException())

        val result = correoArgentinoService.completeForm()

        assertThat(result.success).isFalse()
        assertThat(result.card).isEqualTo(creditCard)
    }
}