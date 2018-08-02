package com.tobert.cube.helpers

import com.tobert.cube.models.Drafter

fun DummyDrafter(name: String = ""): Drafter {
    return Drafter(
            name = name
    )
}