package com.tobert.cube.models

import javax.persistence.*

@Entity
@Table(name = "Cards")
data class Card(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int = 0,

        @Column(nullable = false)
        val name: String,

        @Column(nullable = true)
        val smallImg: String? = null,

        @Column(nullable = true)
        val normalImg: String? = null,

        @Column(nullable = true)
        val borderCropImg: String? = null
)
