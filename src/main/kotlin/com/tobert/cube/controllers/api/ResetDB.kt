package com.tobert.cube.controllers.api

import com.tobert.cube.repositories.DraftRepository
import com.tobert.cube.repositories.DrafterRepository
import com.tobert.cube.repositories.DrafterCardRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ResetDB {
    @Autowired
    lateinit var drafterRepository: DrafterRepository

    @Autowired
    lateinit var draftRepository: DraftRepository

    @Autowired
    lateinit var drafterCardRepository: DrafterCardRepository

    @GetMapping("/reset_db")
    fun reset() {
        drafterRepository.deleteAll()
        draftRepository.deleteAll()
        drafterCardRepository.deleteAll()
    }
}