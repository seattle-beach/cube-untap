package com.tobert.cube.services

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.never
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.tobert.cube.helpers.DummyDrafter
import com.tobert.cube.models.Drafter
import com.tobert.cube.repositories.DrafterRepository
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class DrafterServiceTest {

    private lateinit var mockRepository: DrafterRepository
    private lateinit var subject: DrafterService

    @Before
    fun setup() {
        mockRepository = Mockito.mock(DrafterRepository::class.java)
        subject = DrafterService(mockRepository)
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
}