package com.tobert.cube.helpers

import com.tobert.cube.models.Card


fun DummyCard(name: String = ""): Card {
    return Card(
            name = name
    )
}