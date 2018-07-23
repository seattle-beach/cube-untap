package com.tobert.cube.scryfall


data class CardsResponse(val name: String,
                         val image_uris: CardImages?,
                         val card_faces: List<CardsResponse>?) {

    fun images(): CardImages {
        if (this.image_uris != null) {
            return this.image_uris
        }

        if (this.card_faces != null) {
            return this.card_faces[0].images()
        }

        return CardImages(
                small = "",
                normal = "",
                large = "",
                png = "",
                art_crop = "",
                border_crop = ""
        )
    }
}

data class CardImages(val small: String,
                      val normal: String,
                      val large: String,
                      val png: String,
                      val art_crop: String,
                      val border_crop: String)