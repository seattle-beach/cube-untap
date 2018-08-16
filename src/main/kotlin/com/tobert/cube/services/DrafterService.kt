package com.tobert.cube.services

import com.tobert.cube.models.Card
import com.tobert.cube.models.Drafter
import com.tobert.cube.repositories.DrafterRepository
import org.springframework.stereotype.Service

@Service
class DrafterService(val drafterRepository: DrafterRepository) {
    fun getAllShuffled(): List<Drafter> {
        return drafterRepository.findAll().shuffled()
    }

    fun save(drafter: Drafter) {
        drafterRepository.save(drafter)
    }

    fun findDrafter(drafterName: String): Drafter? {
        return drafterRepository.findByName(drafterName)
    }

    fun create(drafterName: String) {
        if (drafterRepository.findByName(drafterName) == null) {
            drafterRepository.save(Drafter(name = drafterName, seat = null))
        }
    }

    fun pickCard(drafter: Drafter, pickedCard: Card) {
        drafter.pickedCards = drafter.pickedCards.plus(pickedCard)
        drafter.currentPack()!!.cards.remove(pickedCard)

        drafterRepository.save(drafter)
    }
}
