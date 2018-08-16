package com.tobert.cube.helpers

import com.tobert.cube.models.Card
import com.tobert.cube.models.Pack

fun DummyPack(id: Int = 0,
              cards: MutableList<Card> = mutableListOf()
): Pack {
    return Pack(
            id = id,
            cards = cards
    )
}