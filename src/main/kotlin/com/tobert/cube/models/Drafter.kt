package com.tobert.cube.models

import javax.persistence.*

@Entity
@Table(name = "Drafters")
data class Drafter(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int? = null,

        @Column(nullable = false, unique = true)
        val name: String,

        @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
        var packs: List<Pack> = emptyList(),

        @OneToMany(fetch = FetchType.LAZY)
        var pickedCards: List<Card> = emptyList(),

        @Column(nullable = true)
        var seat: Int?
) {

    fun currentPackCards(): List<Card> {
        if (this.packs.isNotEmpty()) {
            return this.packs.first().cards
        }
        return emptyList()
    }

}