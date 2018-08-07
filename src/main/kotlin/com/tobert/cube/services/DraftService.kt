package com.tobert.cube.services

import com.tobert.cube.models.Draft
import com.tobert.cube.repositories.DraftRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class DraftService {
    @Autowired
    lateinit var draftRepository: DraftRepository

    @Autowired
    lateinit var cardService: CardService

    @Autowired
    lateinit var drafterService: DrafterService

    fun startDraft(cardsPerPack: Int) {
        draftRepository.save(Draft())

        val cards = cardService.getAllShuffled().chunked(cardsPerPack)
        val drafters = drafterService.getAllShuffled()

        cards.zip(drafters).forEachIndexed { i, (cards, drafter) ->
            drafter.cards = cards
            drafter.seat = i + 1
            drafterService.save(drafter)
        }
    }
}
