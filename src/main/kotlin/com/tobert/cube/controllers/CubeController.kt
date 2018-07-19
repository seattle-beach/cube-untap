package com.tobert.cube.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.view.RedirectView

@Controller
class CubeController {
    var cubeCards : List<String> = listOf()

    @GetMapping("/")
    fun cube(): ModelAndView {
        val cube = ModelAndView("cube")
        cube.model["cubeCards"] = cubeCards

        return cube
    }

    @PostMapping("/create")
    fun create(@ModelAttribute("cards") cardList:String): RedirectView {
        this.cubeCards = cardList.split("\r")
        return RedirectView("/")
    }
}
