package com.tobert.cube.helpers

import com.tobert.cube.scryfall.CardImages
import com.tobert.cube.scryfall.CardsResponse

fun DummyCardResponse(
        name: String = "Magic Card",
        image_uris: CardImages? = null,
        card_faces: List<CardsResponse>? = null
): CardsResponse {
    return CardsResponse(
            name = name,
            image_uris = image_uris,
            card_faces = card_faces
    )
}

fun DummyImages(
        small: String = "",
        normal: String = "",
        border_crop: String = ""): CardImages {
    return CardImages(
            small = small,
            normal = normal,
            large = "",
            png = "",
            art_crop = "",
            border_crop = border_crop
    )
}