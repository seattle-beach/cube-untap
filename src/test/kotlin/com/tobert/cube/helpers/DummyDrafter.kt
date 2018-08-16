package com.tobert.cube.helpers

import com.tobert.cube.models.Card
import com.tobert.cube.models.Drafter
import com.tobert.cube.models.Pack

fun DummyDrafter(
        name: String = "",
        packs: List<Pack> = emptyList(),
        pickedCards: List<Card> = emptyList()
): Drafter {
    return Drafter(
            name = name,
            packs = packs,
            pickedCards = pickedCards,
            seat = null
    )
}