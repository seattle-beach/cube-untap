package com.tobert.cube.helpers

import com.tobert.cube.models.Card


fun DummyCard(
        name: String = "",
        id: Int = 0,
        borderCropImg: String = ""
): Card {
    return Card(
            id = id,
            name = name,
            borderCropImg = borderCropImg
    )
}