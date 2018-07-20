package com.tobert.cube.scryfall


data class CardsResponse(val image_uris: CardImages,
                         val name: String)

data class CardImages(val small: String,
                      val normal: String,
                      val large: String,
                      val png: String,
                      val art_crop: String,
                      val border_crop: String)