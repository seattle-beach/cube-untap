package com.tobert.cube.services

import com.tobert.cube.models.Card
import com.tobert.cube.repositories.CardRepository
import com.tobert.cube.scryfall.CardImages
import com.tobert.cube.scryfall.CardsResponse
import com.tobert.cube.scryfall.ScryfallClient
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CardServiceClientTest {
    @Mock
    lateinit var scryfallClient: ScryfallClient

    @Mock
    lateinit var cardRepository: CardRepository

    @InjectMocks
    lateinit var subject: CardServiceClient

    var imageURIs = CardImages(
            small = "small",
            normal = "normal",
            large = "large",
            png = "png",
            art_crop = "art_crop",
            border_crop = "border_crop"
    )

    @Test
    fun `it deletes the cards before creating`() {
        val cards = listOf("JuJu Bubble", "Recurring Nightmare")
        subject.createCube(cards)
        verify(cardRepository).deleteAll()
    }

    @Test
    fun `it gets the card images when creating a cube`() {
        val cards = listOf("JuJu Bubble", "Recurring Nightmare")
        val cardResponse = listOf(
                CardsResponse(name = "JuJu Bubble", image_uris = imageURIs),
                CardsResponse(name = "Recurring Nightmare", image_uris = imageURIs)
        )
        `when`(scryfallClient.getCards(cards)).thenReturn(cardResponse)

        subject.createCube(cards)

        verify(cardRepository).saveAll(listOf(
                Card(name = "JuJu Bubble", smallImg = "small", normalImg = "normal", borderCropImg = "border_crop"),
                Card(name = "Recurring Nightmare", smallImg = "small", normalImg = "normal", borderCropImg = "border_crop")
        ))
    }

    @Test
    fun `it can get the cards from a cube`() {
        val cards = listOf(Card(name = "JuJu Bubble"), Card(name = "Recurring Nightmare"))
        `when`(cardRepository.findAll()).thenReturn(cards)
        assertThat(subject.getAll()).isEqualTo(cards)
    }
}

