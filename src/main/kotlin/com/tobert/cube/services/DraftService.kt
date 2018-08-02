package com.tobert.cube.services

import com.tobert.cube.models.Draft
import com.tobert.cube.models.DrafterCard
import com.tobert.cube.repositories.DraftRepository
import com.tobert.cube.repositories.DrafterRepository
import com.tobert.cube.repositories.DrafterCardRepository
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

    @Autowired
    lateinit var drafterCardRepository: DrafterCardRepository

    fun startDraft(cardsPerPack: Int) {
        draftRepository.save(Draft())

        val cards = cardService.getAllShuffled().chunked(cardsPerPack)
        val drafters = draftersRepository.findAll()

        drafters.forEachIndexed { i, drafter ->
            cards[i].map { card -> DrafterCard(card = card, drafter = drafter) }
                    .forEach { draftersCard -> drafterCardRepository.save(draftersCard) }
        }
    }
}
