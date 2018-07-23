package com.tobert.cube.scryfall

import com.tobert.cube.helpers.DummyCardResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import org.springframework.web.client.RestTemplate

@RunWith(MockitoJUnitRunner::class)
class ScryfallClientTest {
    @Mock
    lateinit var mockRestTemplate: RestTemplate

    @InjectMocks
    lateinit var subject: ScryfallClient

    @Test
    fun `getCards retrieves card data for card by name`() {
        val manaDork = DummyCardResponse(name = "Mana Dork")
        val blackLotus = DummyCardResponse(name = "Black Lotus")

        `when`(mockRestTemplate.getForObject("https://api.scryfall.com/cards/named?exact={cardName}",
                CardsResponse::class.java,
                mapOf<String, String>("cardName" to "Mana Dork")
        )).thenReturn(manaDork)


        `when`(mockRestTemplate.getForObject("https://api.scryfall.com/cards/named?exact={cardName}",
                CardsResponse::class.java,
                mapOf<String, String>("cardName" to "Black Lotus")
        )).thenReturn(blackLotus)

        val ret = subject.getCards(listOf("Mana Dork", "Black Lotus"))

        assertThat(ret).isEqualTo(listOf(manaDork, blackLotus))
    }
}
