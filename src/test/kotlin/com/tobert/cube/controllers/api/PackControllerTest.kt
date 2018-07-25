package com.tobert.cube.controllers.api

import com.tobert.cube.models.Card
import com.tobert.cube.repositories.CardRepository
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
class CubeControllerTest {
    @Autowired
    lateinit var mvc: MockMvc

    @MockBean
    lateinit var cardRepository: CardRepository

    @Test
    fun `it gets the pack`() {
        val cards = listOf(Card(name = "Black Lotus", borderCropImg = "card-image.png"))
        `when`(cardRepository.findAll(PageRequest.of(0, 15)))
                .thenReturn(PageImpl(cards))

        mvc.perform(MockMvcRequestBuilders.get("/pack"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful)
                .andExpect(
                        MockMvcResultMatchers.content().json("[\n  {\n    \"name\": \"Black Lotus\",\n    \"image\": \"card-image.png\"\n  }\n]\n")
                )
    }

}