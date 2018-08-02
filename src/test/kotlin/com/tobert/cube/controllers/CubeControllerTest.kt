package com.tobert.cube.controllers

import com.nhaarman.mockito_kotlin.whenever
import com.tobert.cube.helpers.DummyCard
import com.tobert.cube.helpers.DummyDraft
import com.tobert.cube.helpers.DummyDrafter
import com.tobert.cube.repositories.DraftRepository
import com.tobert.cube.repositories.DrafterRepository
import com.tobert.cube.services.CardService
import org.hamcrest.core.IsEqual
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.model
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@RunWith(SpringRunner::class)
@WebMvcTest(controllers = [CubeController::class])
class CubeControllerTest {
    @Autowired
    lateinit var mvc: MockMvc

    @MockBean
    lateinit var mockCardService: CardService

    @MockBean
    lateinit var drafterRepository: DrafterRepository

    @MockBean
    lateinit var draftRepository: DraftRepository

    @Test
    fun `it gets the cube from the repository`() {
        whenever(mockCardService.getAll()).thenReturn(listOf(DummyCard("")))
        whenever(drafterRepository.findAll()).thenReturn(listOf(DummyDrafter("")))
        whenever(draftRepository.findAll()).thenReturn(listOf(DummyDraft()))

        mvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().is2xxSuccessful)
                .andExpect(
                        model().attribute("cubeCards", IsEqual(listOf(DummyCard(""))))
                )
                .andExpect(
                        model().attribute("drafters", IsEqual(listOf(DummyDrafter(""))))
                )
                .andExpect(
                        model().attribute("draft", IsEqual(listOf(DummyDraft())))
                )
    }

    @Test
    fun `it can create cubes`() {
        mvc.perform(
                MockMvcRequestBuilders.post("/createCube")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("cards", "JuJu Bubble\rRecurring Nightmare")
        ).andExpect(
                status().is3xxRedirection
        )

        verify(mockCardService).createCube(listOf("JuJu Bubble", "Recurring Nightmare"))
    }

    @Test
    fun `it filters blank lines`() {
        mvc.perform(
                MockMvcRequestBuilders.post("/createCube")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("cards", "JuJu Bubble\r\r")
        )

        verify(mockCardService).createCube(listOf("JuJu Bubble"))
    }
}

