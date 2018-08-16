package com.tobert.cube.controllers.api

import com.tobert.cube.entity.CardEntity
import com.tobert.cube.services.DrafterService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class PackController : BaseController() {

    @Autowired
    lateinit var drafterService: DrafterService

    @GetMapping("/pack/{username}")
    fun pack(@PathVariable username: String): ResponseEntity<List<CardEntity>> {
        val drafter = drafterService.findDrafter(username)

        if (drafter == null) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }

        val pack = drafter.currentPackCards().map {
            CardEntity(
                    id = it.id,
                    name = it.name,
                    image = it.borderCropImg ?: ""
            )
        }
        return ResponseEntity.ok(pack)
    }
}
