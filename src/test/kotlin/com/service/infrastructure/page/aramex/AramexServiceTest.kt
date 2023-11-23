package com.service.infrastructure.page.aramex

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import com.service.core.domain.creditcard.CreditCard
import com.service.infrastructure.Repositories
import com.service.infrastructure.repository.creditcard.CreditCardRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.mockito.Mockito.*
import org.mockito.kotlin.whenever


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AramexServiceTest {
    private val mockedRepository = mock(CreditCardRepository::class.java)
    private val mockedPage = mock(AramexPage::class.java)
    private val aramexService = AramexService(mockedRepository, mockedPage)
    private lateinit var creditCard: CreditCard

    @BeforeAll
    fun setUp() {
        creditCard = Repositories.creditCardRepository.getRandomCreditCard()
        whenever(mockedRepository.getRandomCreditCard()).thenReturn(creditCard)
    }

    @AfterEach
    fun tearDown() = reset(mockedPage)

    @Test
    fun `should complete the form in the correct order`() {
        aramexService.completeForm()

        val inOrder = inOrder(mockedPage)
        inOrder.verify(mockedPage).navigateTo()
        inOrder.verify(mockedPage).fillFirstName(creditCard.holder.firstName)
        inOrder.verify(mockedPage).fillLastName(creditCard.holder.lastName)
        inOrder.verify(mockedPage).fillCardNumber(creditCard.number)
        inOrder.verify(mockedPage).fillExpirationDate("${creditCard.expiration.getMonth()}/${creditCard.expiration.getYear()}")
        inOrder.verify(mockedPage).fillCVV(creditCard.cvv)
        inOrder.verify(mockedPage).clickAccept()
        inOrder.verify(mockedPage).waitForSMSInput()
    }

    @Test
    fun `should return a result after a successful form completion`() {
        whenever(mockedPage.waitForSMSInput()).thenReturn(true)

        val result = aramexService.completeForm()

        assertThat(result.success).isTrue()
        assertThat(result.card).isEqualTo(creditCard)
    }

    @Test
    fun `should return a result after an unsuccessful form completion`() {
        whenever(mockedPage.fillLastName(anyString())).thenThrow(RuntimeException())

        val result = aramexService.completeForm()

        assertThat(result.success).isFalse()
        assertThat(result.card).isEqualTo(creditCard)
    }
}