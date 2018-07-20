package com.tobert.cube.controllers

import com.tobert.cube.models.Card
import com.tobert.cube.repositories.CardRepository
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
    lateinit var cardRepository: CardRepository

    @GetMapping("/")
    fun cube(): ModelAndView {
        val cube = ModelAndView("cube")
        cube.model["cubeCards"] = cardRepository.findAll()

        return cube
    }

    @PostMapping("/create")
    fun create(@ModelAttribute("cards") cardList:String): RedirectView {
        val cubeCards = cardList.split("\r").map { Card(name = it) }
        cardRepository.deleteAll()
        cardRepository.saveAll(cubeCards)
        return RedirectView("/")
    }
}
