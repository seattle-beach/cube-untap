package com.tobert.cube.controllers.api

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import com.tobert.cube.helpers.DummyCard
import com.tobert.cube.helpers.DummyDrafter
import com.tobert.cube.helpers.DummyPack
import com.tobert.cube.services.DrafterService
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
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
    lateinit var drafterService: DrafterService

    @Test
    fun `it gets the first pack for the drafter`() {
        val firstPack = DummyPack(
                id = 1,
                cards = mutableListOf(DummyCard(id = 1, name = "Black Lotus", borderCropImg = "card-image.png"))
        )

        val secondPack = DummyPack(
                id = 1,
                cards = mutableListOf(DummyCard(id = 2, name = "Mox emerald", borderCropImg = "other-card-image.png"))
        )

        val drafter = DummyDrafter(packs = mutableListOf(firstPack, secondPack))

        whenever(drafterService.findDrafter("Toby")).thenReturn(drafter)

        mvc.perform(MockMvcRequestBuilders.get("/api/pack/Toby"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful)
                .andExpect(
                        MockMvcResultMatchers.content().json("[\n  {\n    \"id\": 1,   \n    \"name\": \"Black Lotus\",\n    \"image\": \"card-image.png\"\n  }\n]\n")
                )
    }

    @Test
    fun `returns an empty array when the drafter does not have cards`() {
        whenever(drafterService.findDrafter(any())).thenReturn(DummyDrafter())

        mvc.perform(MockMvcRequestBuilders.get("/api/pack/Toby"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful)
                .andExpect(
                        MockMvcResultMatchers.content().json("[]")
                )
    }


    @Test
    fun `returns an error when the drafter is not found`() {
        whenever(drafterService.findDrafter(any())).thenReturn(null)

        mvc.perform(MockMvcRequestBuilders.get("/api/pack/Toby"))
                .andExpect(MockMvcResultMatchers.status().isNotFound)
    }
}