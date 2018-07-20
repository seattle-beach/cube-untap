package com.tobert.cube.services

import com.tobert.cube.models.Card
import com.tobert.cube.repositories.CardRepository
import com.tobert.cube.scryfall.ScryfallClient
import org.springframework.stereotype.Service

interface CardService {
    fun createCube(cards: List<String>)
    fun getAll(): List<Card>
}

@Service
class CardServiceClient(
        val scryfallClient: ScryfallClient,
        val cardRepository: CardRepository
) : CardService {

    override fun createCube(cards: List<String>) {
        cardRepository.deleteAll()

        cardRepository.saveAll(
                scryfallClient.getCards(cards).map {
                    Card(
                            name = it.name,
                            smallImg = it.image_uris.small,
                            normalImg = it.image_uris.normal,
                            borderCropImg = it.image_uris.border_crop
                    )
                }
        )
    }

    override fun getAll(): List<Card> {
        return cardRepository.findAll()
    }
}

