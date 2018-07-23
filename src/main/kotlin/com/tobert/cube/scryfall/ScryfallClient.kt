package com.tobert.cube.scryfall

import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import java.util.concurrent.TimeUnit

@Component
class ScryfallClient(val restTemplate: RestTemplate){
    val templateURL: String = "https://api.scryfall.com/cards/named?exact={cardName}"

    fun getCards(cardNames: List<String>): List<CardsResponse> {
        return cardNames.mapNotNull {
            TimeUnit.MILLISECONDS.sleep(50)// scryfall doesn't want to get pinged to crap
            restTemplate.getForObject(
                    templateURL,
                    CardsResponse::class.java,
                    mapOf<String, String>("cardName" to it)
            )

        }
    }
}
