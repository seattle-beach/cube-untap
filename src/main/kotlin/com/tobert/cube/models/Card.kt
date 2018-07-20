package com.tobert.cube.models

import javax.persistence.*

@Entity
@Table(name = "Cards")
data class Card(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int? = null,

        @Column(nullable = false)
        val name: String
)
