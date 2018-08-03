package com.tobert.cube.services

import com.nhaarman.mockito_kotlin.verify
import com.tobert.cube.helpers.DummyDrafter
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
}