package com.tobert.cube.controllers.api

import com.tobert.cube.repositories.DraftRepository
import com.tobert.cube.repositories.DrafterRepository
import com.tobert.cube.repositories.PackRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ResetDB: BaseController() {
    @Autowired
    lateinit var drafterRepository: DrafterRepository

    @Autowired
    lateinit var draftRepository: DraftRepository

    @Autowired
    lateinit var packRepository: PackRepository

    @GetMapping("/reset_db")
    fun reset() {
        drafterRepository.deleteAll()
        draftRepository.deleteAll()
        packRepository.deleteAll()
    }
}