package com.tobert.cube.controllers.api

import com.nhaarman.mockito_kotlin.whenever
import com.tobert.cube.helpers.DummyDrafter
import com.tobert.cube.models.Drafter
import com.tobert.cube.repositories.DrafterRepository
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.never
import org.mockito.Mockito.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@RunWith(SpringRunner::class)
@WebMvcTest(controllers = [DrafterController::class])
class DrafterControllerTest {
    @Autowired
    lateinit var mvc: MockMvc

    @MockBean
    lateinit var mockDrafterRepository: DrafterRepository

    @Test
    fun `it can create a drafter`() {
        whenever(mockDrafterRepository.findByName("Toby")).thenReturn(null)

        mvc.perform(
                MockMvcRequestBuilders.post("/drafter/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n  \"drafter\": \"Toby\"\n}")
        ).andExpect(
                MockMvcResultMatchers.status().isCreated
        )

        verify(mockDrafterRepository).save(Drafter(name= "Toby", seat = null))
    }

    @Test
    fun `does not create a drafter if it already exists`() {
        whenever(mockDrafterRepository.findByName("Toby")).thenReturn(DummyDrafter())

        mvc.perform(
                MockMvcRequestBuilders.post("/drafter/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n  \"drafter\": \"Toby\"\n}")
        ).andExpect(
                MockMvcResultMatchers.status().isCreated
        )

        verify(mockDrafterRepository, never()).save(Drafter(name= "Toby", seat = null))
    }
}