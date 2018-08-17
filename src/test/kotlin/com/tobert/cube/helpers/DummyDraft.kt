package com.tobert.cube.helpers

import com.tobert.cube.models.Draft
import com.tobert.cube.models.Drafter

fun DummyDraft(
        drafters: List<Drafter> = emptyList()
): Draft {
    return Draft(
            drafters = drafters
    )
}