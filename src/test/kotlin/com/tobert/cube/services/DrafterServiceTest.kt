package com.tobert.cube.services

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.never
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.tobert.cube.helpers.DummyCard
import com.tobert.cube.helpers.DummyDrafter
import com.tobert.cube.helpers.DummyPack
import com.tobert.cube.models.Drafter
import com.tobert.cube.repositories.DrafterRepository
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class DrafterServiceTest {

    private lateinit var mockRepository: DrafterRepository
    private lateinit var mockDraftService: DraftService
    private lateinit var subject: DrafterService

    @Before
    fun setup() {
        mockRepository = Mockito.mock(DrafterRepository::class.java)
        mockDraftService = Mockito.mock(DraftService::class.java)
        subject = DrafterService(mockRepository, mockDraftService)
    }

    @Test
    fun getAllShuffled() {
        subject.getAllShuffled()

        verify(mockRepository).findAll()
    }

    @Test
    fun save() {
        val drafter = DummyDrafter(name = "a drafter")

        subject.save(drafter)

        verify(mockRepository).save(drafter)
    }

    @Test
    fun findDrafter() {
        subject.findDrafter("name")
        verify(mockRepository).findByName("name")
    }


    @Test
    fun `it creates a drafter`() {
        whenever(mockRepository.findByName("Toby")).thenReturn(null)

        subject.create("Toby")

        verify(mockRepository).save(Drafter(name = "Toby", seat = null))
    }

    @Test
    fun `it does not create a drafter if it already exists`() {
        whenever(mockRepository.findByName("Toby")).thenReturn(DummyDrafter())

        subject.create("Toby")

        verify(mockRepository, never()).save(any<Drafter>())
    }

    @Test
    fun `it picks the cards and passes the pack`() {
        val card = DummyCard(id = 2)

        val drafter = DummyDrafter(
                packs = mutableListOf(DummyPack(id = 1, cards = mutableListOf(card)), DummyPack(id = 2)),
                pickedCards = listOf(DummyCard(id = 1))
        )

        subject.pickCard(drafter, card)

        val expectedDrafter = DummyDrafter(
                packs = mutableListOf(DummyPack(id = 2)),
                pickedCards = listOf(DummyCard(id = 1), DummyCard(id = 2))
        )

        verify(mockRepository).save(expectedDrafter)
        verify(mockDraftService).passDraftersPack(expectedDrafter, DummyPack(id = 1))
    }
}