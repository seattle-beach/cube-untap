package com.tobert.cube.scryfall

import org.springframework.stereotype.Component

@Component
class ScryfallClient{
    fun getCards(cardNames: List<String>): List<CardsResponse> {
        return emptyList()
    }
}
