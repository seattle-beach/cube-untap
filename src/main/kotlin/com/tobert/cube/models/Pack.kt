package com.tobert.cube.models

import javax.persistence.*

@Entity
@Table(name = "Packs")
data class Pack(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int? = null,

        @OneToMany(fetch = FetchType.LAZY)
        var cards: MutableList<Card> = mutableListOf()
)
