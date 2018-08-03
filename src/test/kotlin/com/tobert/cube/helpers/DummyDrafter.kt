package com.tobert.cube.helpers

import com.tobert.cube.models.Card
import com.tobert.cube.models.Drafter

fun DummyDrafter(name: String = "", cards: List<Card> = emptyList()): Drafter {
    return Drafter(
            name = name,
            cards = cards,
            seat = null
    )
}