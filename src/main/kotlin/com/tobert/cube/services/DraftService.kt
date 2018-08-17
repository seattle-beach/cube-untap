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
        draftRepository.save(Draft())

        val cards = cardService.getAllShuffled().chunked(cardsPerPack)
        val drafters = drafterService.getAllShuffled()

        cards.zip(drafters).forEachIndexed { i, (cards, drafter) ->
            drafter.packs = arrayListOf(Pack(cards = cards.toMutableList()))
            drafter.seat = i + 1
            drafterService.save(drafter)
        }
    }

    fun passDraftersPack(passingDrafter: Drafter, packToPass: Pack) {

        /*
        val draft = draftRepository.findById(1).get()

        val thisDrafterIndex = draft.drafters.indexOf(drafter)
        val passToDrafterIndex = (thisDrafterIndex + 1) % draft.drafters.size

        draft.drafters[passToDrafterIndex].packs.add(draft.drafters[thisDrafterIndex].currentPack()!!)
        drafter.packs.removeAt(0)

        draftRepository.save(draft)*/
    }
}
