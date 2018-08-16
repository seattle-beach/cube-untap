package com.tobert.cube.services

import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.tobert.cube.helpers.DummyCard
import com.tobert.cube.helpers.DummyDrafter
import com.tobert.cube.helpers.DummyPack
import com.tobert.cube.models.Draft
import com.tobert.cube.models.Drafter
import com.tobert.cube.models.Pack
import com.tobert.cube.repositories.DraftRepository
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
    lateinit var drafterService: DrafterService

    @InjectMocks
    lateinit var subject: DraftService

    @Test
    fun `it creates a new draft`() {
        subject.startDraft(1)
        verify(draftRepository).save(Draft())
    }

    @Test
    fun `it assigns cards to the current drafters`() {
        val firstCard = DummyCard(name = "first-card")
        val secondCard = DummyCard(name = "second-card")
        val thirdCard = DummyCard(name = "third-card")
        val fourthCard = DummyCard(name = "fourth-card")

        val firstDrafter = DummyDrafter(name = "Toby")
        val secondDrafter = DummyDrafter(name = "Omeed")

        whenever(drafterService.getAllShuffled()).thenReturn(listOf(firstDrafter, secondDrafter))
        whenever(cardService.getAllShuffled()).thenReturn(listOf(firstCard, secondCard, thirdCard, fourthCard))

        subject.startDraft(2)

        verify(drafterService).save(Drafter(name = "Toby", seat = 1, packs = mutableListOf(Pack(cards = mutableListOf(firstCard, secondCard)))))
        verify(drafterService).save(Drafter(name = "Omeed", seat = 2, packs = mutableListOf(Pack(cards = mutableListOf(thirdCard, fourthCard)))))
    }
}