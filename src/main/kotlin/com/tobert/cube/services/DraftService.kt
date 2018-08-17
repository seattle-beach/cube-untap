package com.tobert.cube.services

import com.tobert.cube.models.Draft
import com.tobert.cube.models.Drafter
import com.tobert.cube.models.Pack
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
        val draft = Draft()

        val cards = cardService.getAllShuffled().chunked(cardsPerPack)
        val drafters = drafterService.getAllShuffled()

        cards.zip(drafters).forEach { (cards, drafter) ->
            drafter.packs = arrayListOf(Pack(cards = cards.toMutableList()))
            draft.drafters = draft.drafters.plus(drafter)
        }

        draftRepository.save(draft)
    }

    fun passDraftersPack(passingDrafter: Drafter) {
        val draft = draftRepository.findAll().first()

        val numberOfSeats = draft.drafters.size
        val passingDrafterSeat = draft.drafters.indexOf(passingDrafter)
        val receivingDrafterSeat = (passingDrafterSeat + 1) % numberOfSeats

        val packToPass = draft.drafters[passingDrafterSeat].removeCurrentPack()
        draft.drafters[receivingDrafterSeat].addPack(packToPass)

        draftRepository.save(draft)
    }
}
