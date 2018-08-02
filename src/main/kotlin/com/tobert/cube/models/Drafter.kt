package com.tobert.cube.models

import javax.persistence.*

@Entity
@Table(name = "Drafters")
data class Drafter(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int? = null,

        @Column(nullable = false, unique=true)
        val name: String,

        @OneToMany(fetch = FetchType.EAGER)
        var cards: List<Card> = emptyList()
)