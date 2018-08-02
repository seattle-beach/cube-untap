package com.tobert.cube.models

import javax.persistence.*


@Entity
@Table(name = "Drafts")
data class Draft(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int? = null
)