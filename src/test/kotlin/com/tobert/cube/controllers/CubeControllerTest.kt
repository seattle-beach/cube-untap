package com.tobert.cube.controllers

import com.tobert.cube.models.Card
import com.tobert.cube.services.CardService
import org.hamcrest.core.IsEqual
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
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

    @Test
    fun `it gets the cube from the repository`() {
        mvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().is2xxSuccessful)
                .andExpect(
                        model().attribute("cubeCards", IsEqual(listOf<String>()))
                )

        verify(mockCardService).getAll()
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

        val expectedCards = listOf("JuJu Bubble", "Recurring Nightmare")

        verify(mockCardService).createCube(expectedCards)
    }
}

