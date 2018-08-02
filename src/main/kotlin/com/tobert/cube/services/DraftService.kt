package com.tobert.cube.services

import com.tobert.cube.models.Draft
import com.tobert.cube.repositories.DraftRepository
import com.tobert.cube.repositories.DrafterRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class DraftService {
    @Autowired
    lateinit var draftRepository: DraftRepository

    @Autowired
    lateinit var cardService: CardService

    @Autowired
    lateinit var draftersRepository: DrafterRepository

    fun startDraft(cardsPerPack: Int) {
        draftRepository.save(Draft())

        val cards = cardService.getAllShuffled().chunked(cardsPerPack)
        val drafters = draftersRepository.findAll()

        cards.zip(drafters).forEach { (cards, drafter) ->
            drafter.cards = cards
            draftersRepository.save(drafter)
        }
    }
}
