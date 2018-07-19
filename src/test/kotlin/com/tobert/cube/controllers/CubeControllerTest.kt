package com.tobert.cube.controllers

import org.hamcrest.core.IsEqual
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.model
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.result.isEqualTo

@RunWith(SpringRunner::class)
@WebMvcTest(controllers = [CubeController::class])
class CubeControllerTest {
    //test
    // Sends request to /, nothing in var
    // POSTS to /set_cube, gets a redirect
    // request to /, has things in var matching post

    @Autowired
    lateinit var mvc: MockMvc

    @Before
    fun setUp() {
    }

    @Test
    fun `it can create cubes`() {
        mvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().is2xxSuccessful)
                .andExpect(
                        model().attribute("cubeCards", IsEqual(listOf<String>()))
                )

        mvc.perform(
                MockMvcRequestBuilders.post("/create")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("cards", "JuJu Bubble\rRecurring Nightmare")
        ).andExpect(
                status().is3xxRedirection
        )

        mvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().is2xxSuccessful)
                .andExpect(
                        model().attribute(
                                "cubeCards", IsEqual(listOf("JuJu Bubble", "Recurring Nightmare"))
                        )
                )
    }
}

