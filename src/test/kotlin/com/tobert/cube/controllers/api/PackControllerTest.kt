package com.tobert.cube.controllers.api

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import com.tobert.cube.helpers.DummyDrafter
import com.tobert.cube.models.Card
import com.tobert.cube.repositories.DrafterRepository
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@RunWith(SpringRunner::class)
@WebMvcTest(controllers = [PackController::class])
class PackControllerTest {
    @Autowired
    lateinit var mvc: MockMvc

    @MockBean
    lateinit var drafterRepository: DrafterRepository

    @Test
    fun `it gets the pack for the drafter`() {
        val cards = listOf(Card(name = "Black Lotus", borderCropImg = "card-image.png"))
        val drafter = DummyDrafter(cards = cards)

        whenever(drafterRepository.findByName("Toby")).thenReturn(drafter)

        mvc.perform(MockMvcRequestBuilders.get("/api/pack/Toby"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful)
                .andExpect(
                        MockMvcResultMatchers.content().json("[\n  {\n    \"name\": \"Black Lotus\",\n    \"image\": \"card-image.png\"\n  }\n]\n")
                )
    }

    @Test
    fun `returns an empty array when the drafter does not have cards`() {
        whenever(drafterRepository.findByName(any())).thenReturn(DummyDrafter())

        mvc.perform(MockMvcRequestBuilders.get("/api/pack/Toby"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful)
                .andExpect(
                        MockMvcResultMatchers.content().json("[]")
                )
    }


    @Test
    fun `returns an error when the drafter is not found`() {
        whenever(drafterRepository.findByName(any())).thenReturn(null)

        mvc.perform(MockMvcRequestBuilders.get("/api/pack/Toby"))
                .andExpect(MockMvcResultMatchers.status().isNotFound)
    }
}