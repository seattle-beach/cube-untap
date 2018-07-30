package com.tobert.cube.controllers

import com.tobert.cube.repositories.DrafterRepository
import com.tobert.cube.services.CardService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.view.RedirectView

@Controller
class CubeController {
    @Autowired
    lateinit var cardService: CardService

    @Autowired
    lateinit var drafterRepository: DrafterRepository

    @GetMapping("/")
    fun cube(): ModelAndView {
        val cube = ModelAndView("cube")
        cube.model["cubeCards"] = cardService.getAll()
        cube.model["drafters"] = drafterRepository.findAll()

        return cube
    }

    @PostMapping("/createCube")
    fun create(@ModelAttribute("cards") cardList:String): RedirectView {
        val cubeCards = cardList.lines().filter { s -> s != "" }
        cardService.createCube(cubeCards)
        return RedirectView("/")
    }
}
