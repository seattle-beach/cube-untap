package com.tobert.cube.controllers.api

import com.tobert.cube.repositories.DrafterRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ResetDB {
    @Autowired
    lateinit var drafterRepository: DrafterRepository

    @GetMapping("/reset_db")
    fun reset() {
        drafterRepository.deleteAll()
    }
}