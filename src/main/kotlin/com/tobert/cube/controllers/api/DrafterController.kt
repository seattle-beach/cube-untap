package com.tobert.cube.controllers.api

import com.tobert.cube.models.Drafter
import com.tobert.cube.repositories.DrafterRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class DrafterController {
    @Autowired
    lateinit var drafterRepository: DrafterRepository

    @PostMapping("/drafter/create")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@ModelAttribute("drafter") drafter: String) {
        drafterRepository.save(Drafter(name = drafter))
    }
}

