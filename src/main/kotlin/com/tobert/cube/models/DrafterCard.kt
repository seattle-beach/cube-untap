package com.tobert.cube.models

import javax.persistence.*


@Entity
@Table(name = "DrafterCard")
data class DrafterCard(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int? = null,

        @ManyToOne
        val card: Card,

        @ManyToOne
        val drafter: Drafter
)