package com.tobert.cube.services

import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.tobert.cube.helpers.DummyCard
import com.tobert.cube.helpers.DummyDraft
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

        verify(draftRepository).save(
                DummyDraft(
                        drafters = listOf(
                                Drafter(id = 0, name = "Toby", packs = mutableListOf(Pack(cards = mutableListOf(firstCard, secondCard)))),
                                Drafter(id = 0, name = "Omeed", packs = mutableListOf(Pack(cards = mutableListOf(thirdCard, fourthCard))))
                        )
                )
        )
    }

    @Test
    fun `it passes a drafters pack to the next drafter`() {
        val passingPack = DummyPack(id = 1)
        val passingDrafter = DummyDrafter(id = 1, packs = mutableListOf(passingPack, DummyPack(id = 2)))
        val receivingDrafter = DummyDrafter(id = 2, packs = mutableListOf(DummyPack(id = 3)))

        whenever(draftRepository.findAll())
                .thenReturn(listOf(DummyDraft(drafters = listOf(passingDrafter, receivingDrafter))))

        subject.passDraftersPack(passingDrafter)

        verify(draftRepository).save(
                DummyDraft(
                        drafters = listOf(
                                Drafter(id = 1, packs = mutableListOf(DummyPack(id = 2)), name = ""),
                                Drafter(id = 2, packs = mutableListOf(DummyPack(id = 3), passingPack), name = "")
                        )
                )
        )
    }

    @Test
    fun `it passes the last drafters pack to the first drafter`() {
        val passingPack = DummyPack(id = 1)
        val passingDrafter = DummyDrafter(id = 1, packs = mutableListOf(passingPack, DummyPack(id = 2)))
        val receivingDrafter = DummyDrafter(id = 2, packs = mutableListOf(DummyPack(id = 3)))

        whenever(draftRepository.findAll())
                .thenReturn(listOf(DummyDraft(drafters = listOf(receivingDrafter, passingDrafter))))

        subject.passDraftersPack(passingDrafter)

        verify(draftRepository).save(
                DummyDraft(
                        drafters = listOf(
                                Drafter(id = 2, packs = mutableListOf(DummyPack(id = 3), passingPack), name = ""),
                                Drafter(id = 1, packs = mutableListOf(DummyPack(id = 2)), name = "")
                        )
                )
        )
    }
}