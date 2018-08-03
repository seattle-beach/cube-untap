package com.tobert.cube.controllers.api

import com.tobert.cube.models.Drafter
import com.tobert.cube.repositories.DrafterRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
class DrafterController: BaseController() {
    @Autowired
    lateinit var drafterRepository: DrafterRepository

    @PostMapping("/drafter/create")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody drafterRequest: DrafterRequest ) {
        if (drafterRepository.findByName(drafterRequest.drafter) == null) {
            drafterRepository.save(Drafter(name = drafterRequest.drafter, seat = null))
        }
    }
}

data class DrafterRequest (val drafter: String)

