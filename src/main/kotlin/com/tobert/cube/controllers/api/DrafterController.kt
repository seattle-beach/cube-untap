package com.tobert.cube.controllers.api

import com.tobert.cube.entity.CardEntity
import com.tobert.cube.services.DrafterService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class DrafterController : BaseController() {
    @Autowired
    lateinit var drafterService: DrafterService

    @PostMapping("/drafter/create")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody drafterRequest: DrafterRequest) {
        drafterService.create(drafterRequest.drafter)
    }

    @PostMapping("/drafter/{drafterName}/pickCard")
    fun pickCard(@PathVariable drafterName: String,
                 @RequestBody pickCardRequest: PickCardRequest): ResponseEntity<Void> {

        val drafter = drafterService.findDrafter(drafterName)
        if (drafter == null) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }

        val pickedCard = drafter.cardFromCurrentPack(pickCardRequest.cardId)
        if (pickedCard == null) {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }

        drafterService.pickCard(drafter, pickedCard)

        return ResponseEntity(HttpStatus.CREATED)
    }


    @GetMapping("/drafter/{drafterName}/pickedCards")
    fun pickedCards(@PathVariable drafterName: String): ResponseEntity<List<CardEntity>> {
        val drafter = drafterService.findDrafter(drafterName)
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

