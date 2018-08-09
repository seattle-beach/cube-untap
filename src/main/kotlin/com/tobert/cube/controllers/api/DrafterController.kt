package com.tobert.cube.controllers.api

import com.tobert.cube.entity.CardEntity
import com.tobert.cube.models.Drafter
import com.tobert.cube.repositories.CardRepository
import com.tobert.cube.repositories.DrafterRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class DrafterController : BaseController() {
    @Autowired
    lateinit var drafterRepository: DrafterRepository
    @Autowired
    lateinit var cardRepository: CardRepository

    @PostMapping("/drafter/create")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody drafterRequest: DrafterRequest) {
        if (drafterRepository.findByName(drafterRequest.drafter) == null) {
            drafterRepository.save(Drafter(name = drafterRequest.drafter, seat = null))
        }
    }

    @PostMapping("/drafter/{drafterName}/pickCard")
    fun pickCard(@PathVariable drafterName: String,
                 @RequestBody pickCardRequest: PickCardRequest): ResponseEntity<Void> {

        val drafter = drafterRepository.findByName(drafterName)
        if (drafter == null) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }

        val pickedCard = cardRepository.findById(pickCardRequest.cardId)
        if (!pickedCard.isPresent) {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }

        drafter.pickedCards = drafter.pickedCards.plus(pickedCard.get())

        drafterRepository.save(drafter)

        return ResponseEntity(HttpStatus.CREATED)
    }


    @GetMapping("/drafter/{drafterName}/pickedCards")
    fun pickedCards(@PathVariable drafterName: String): ResponseEntity<List<CardEntity>> {
        val drafter = drafterRepository.findByName(drafterName)
        if (drafter == null) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }

        val pickedCards = drafter.pickedCards.map {
            CardEntity(
                    id = it.id,
                    name = it.name,
                    image = it.borderCropImg ?: ""
            )
        }

        return ResponseEntity.ok(pickedCards)
    }
}

data class DrafterRequest(val drafter: String)
data class PickCardRequest(val cardId: Int)

