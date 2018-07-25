package com.tobert.cube.controllers.api

import com.tobert.cube.entity.CardEntity
import com.tobert.cube.repositories.CardRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PackController {

    @Autowired
    lateinit var cardRepository: CardRepository

    @GetMapping("/pack")
    fun pack(): List<CardEntity> {
        val cards = cardRepository.findAll(
                PageRequest.of(0, 15)
        ).content

        return cards.map {
            CardEntity(
                    name = it.name,
                    image = it.borderCropImg ?: ""
            )
        }
    }

}
