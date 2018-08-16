package com.tobert.cube.controllers.api

import com.nhaarman.mockito_kotlin.doNothing
import com.nhaarman.mockito_kotlin.whenever
import com.tobert.cube.helpers.DummyCard
import com.tobert.cube.helpers.DummyDrafter
import com.tobert.cube.helpers.DummyPack
import com.tobert.cube.services.DrafterService
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers


@RunWith(SpringRunner::class)
@WebMvcTest(controllers = [DrafterController::class])
class DrafterControllerTest {
    @Autowired
    lateinit var mvc: MockMvc

    @MockBean
    lateinit var mockDrafterService: DrafterService

    @Test
    fun `it can create a drafter`() {
        doNothing().whenever(mockDrafterService).create("Toby")

        mvc.perform(
                MockMvcRequestBuilders.post("/api/drafter/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n  \"drafter\": \"Toby\"\n}")
        ).andExpect(
                MockMvcResultMatchers.status().isCreated
        )

        verify(mockDrafterService).create("Toby")
    }

    @Test
    fun `returns a not found error when the drafter is not found`() {
        whenever(mockDrafterService.findDrafter("LSV")).thenReturn(null)

        mvc.perform(
                MockMvcRequestBuilders.post("/api/drafter/LSV/pickCard")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n    \"cardId\": 2\n}")
        ).andExpect(MockMvcResultMatchers.status().isNotFound)
    }

    @Test
    fun `returns a bad request when the card is not in drafters current pack`() {
        val currentPack = DummyPack(cards = mutableListOf(DummyCard(id = 1)))
        val nextPack = DummyPack(cards = mutableListOf(DummyCard(id = 2)))
        val drafter = DummyDrafter(packs = listOf(currentPack, nextPack))

        whenever(mockDrafterService.findDrafter("LSV")).thenReturn(drafter)

        mvc.perform(
                MockMvcRequestBuilders.post("/api/drafter/LSV/pickCard")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n    \"cardId\": 2\n}")
        ).andExpect(MockMvcResultMatchers.status().isBadRequest)
    }


    @Test
    fun `it adds a card to a drafters picked cards`() {
        val pack = DummyPack(cards = mutableListOf(DummyCard(id = 2)))
        val drafter = DummyDrafter(packs = listOf(pack))
        whenever(mockDrafterService.findDrafter("LSV")).thenReturn(drafter)

        mvc.perform(
                MockMvcRequestBuilders.post("/api/drafter/LSV/pickCard")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n    \"cardId\": 2\n}")
        ).andExpect(
                MockMvcResultMatchers.status().isCreated
        )

        verify(mockDrafterService).pickCard(drafter, DummyCard(id = 2))
    }

    @Test
    fun `it returns picked cards for a drafter`() {
        val drafter = DummyDrafter(
                name = "LSV",
                pickedCards = listOf(
                        DummyCard(id = 1, name = "Black Lotus", borderCropImg = "card-image.png"),
                        DummyCard(id = 2, name = "Mox emerald", borderCropImg = "other-card-image.png")
                )
        )

        whenever(mockDrafterService.findDrafter("LSV")).thenReturn(drafter)

        mvc.perform(MockMvcRequestBuilders.get("/api/drafter/LSV/pickedCards"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful)
                .andExpect(
                        MockMvcResultMatchers.content().json("[\n  {\n    \"id\": 1,\n    \"name\": \"Black Lotus\",\n    \"image\": \"card-image.png\"\n  },\n  {\n    \"id\": 2,\n    \"name\": \"Mox emerald\",\n    \"image\": \"other-card-image.png\"\n  }\n]")
                )
    }

    @Test
    fun `it returns a 404 when the drafter does not exist`() {
        whenever(mockDrafterService.findDrafter("LSV")).thenReturn(null)

        mvc.perform(MockMvcRequestBuilders.get("/api/drafter/LSV/pickedCards"))
                .andExpect(MockMvcResultMatchers.status().isNotFound)
    }
}