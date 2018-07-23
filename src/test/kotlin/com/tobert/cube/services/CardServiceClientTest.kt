package com.tobert.cube.services

import com.tobert.cube.helpers.DummyCardResponse
import com.tobert.cube.helpers.DummyImages
import com.tobert.cube.models.Card
import com.tobert.cube.repositories.CardRepository
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

    @Test
    fun `it deletes the cards before creating`() {
        val cards = listOf("JuJu Bubble", "Recurring Nightmare")
        subject.createCube(cards)
        verify(cardRepository).deleteAll()
    }

    @Test
    fun `it gets the card images when creating a cube`() {
        val images = DummyImages(small = "small", normal = "normal", border_crop = "border_crop")
        val cards = listOf("JuJu Bubble", "Recurring Nightmare")
        val cardResponse = listOf(
                DummyCardResponse(
                        name = "JuJu Bubble",
                        card_faces = listOf(DummyCardResponse(image_uris = images))
                ),
                DummyCardResponse(
                        name = "Recurring Nightmare",
                        card_faces = listOf(DummyCardResponse(image_uris = images))
                )
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

