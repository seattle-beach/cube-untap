package com.tobert.cube.services

import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.tobert.cube.helpers.DummyCard
import com.tobert.cube.helpers.DummyDrafter
import com.tobert.cube.models.Draft
import com.tobert.cube.models.DrafterCard
import com.tobert.cube.repositories.DraftRepository
import com.tobert.cube.repositories.DrafterRepository
import com.tobert.cube.repositories.DrafterCardRepository
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DraftServiceTest {
    @Mock
    lateinit var draftRepository: DraftRepository

    @Mock
    lateinit var cardService: CardService

    @Mock
    lateinit var drafterRepository: DrafterRepository

    @Mock
    lateinit var drafterCardRepository: DrafterCardRepository

    @InjectMocks
    lateinit var subject: DraftService

    @Test
    fun `it creates a new draft`() {
        subject.startDraft(1)
        verify(draftRepository).save(Draft())
    }

    @Test
    fun `it assigns cards to the current drafters`() {
        val firstCard = DummyCard(name="first-card")
        val secondCard = DummyCard(name="second-card")
        val thirdCard = DummyCard(name="third-card")
        val fourthCard = DummyCard(name="fourth-card")

        val firstDrafter = DummyDrafter(name="Toby")
        val secondDrafter = DummyDrafter(name="Omeed")

        whenever(drafterRepository.findAll()).thenReturn(listOf(firstDrafter, secondDrafter))
        whenever(cardService.getAllShuffled()).thenReturn(listOf(firstCard, secondCard, thirdCard, fourthCard))

        subject.startDraft(2)

        verify(drafterCardRepository).save(DrafterCard(card = firstCard, drafter = firstDrafter))
        verify(drafterCardRepository).save(DrafterCard(card = secondCard, drafter = firstDrafter))
        verify(drafterCardRepository).save(DrafterCard(card = thirdCard, drafter = secondDrafter))
        verify(drafterCardRepository).save(DrafterCard(card = fourthCard, drafter = secondDrafter))
    }
}