package com.tobert.cube.controllers

import com.tobert.cube.services.DraftService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.servlet.view.RedirectView

@Controller
class DraftController {

    @Autowired
    lateinit var draftService: DraftService

    @PostMapping("/draft/start")
    fun start(): RedirectView {
        draftService.startDraft(15)
        return RedirectView("/")
    }
}