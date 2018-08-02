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
        @JoinTable(name="DraftersCards",
                joinColumns=[JoinColumn(name = "drafter", referencedColumnName="id")])
        val cards: List<Card> = emptyList()
)