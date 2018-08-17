package com.tobert.cube.helpers

import com.tobert.cube.models.Card
import com.tobert.cube.models.Drafter
import com.tobert.cube.models.Pack

fun DummyDrafter(
        id: Int = 0,
        name: String = "",
        packs: MutableList<Pack> = mutableListOf(),
        pickedCards: List<Card> = emptyList(),
        seat: Int? = null
): Drafter {
    return Drafter(
            id = id,
            name = name,
            packs = packs,
            pickedCards = pickedCards,
            seat = seat
    )
}