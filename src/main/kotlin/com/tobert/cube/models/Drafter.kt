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
        var packs: MutableList<Pack> = mutableListOf(),

        @OneToMany(fetch = FetchType.LAZY)
        var pickedCards: List<Card> = emptyList(),

        @Column(nullable = true)
        var seat: Int?
) {

    fun currentPack(): Pack? {
        return when {
            this.packs.isNotEmpty() -> this.packs.first()
            else -> null
        }
    }

    fun cardFromCurrentPack(cardId: Int): Card? {
        return when {
            this.packs.isNotEmpty() -> this.packs.first().cards.find { it.id == cardId }
            else -> null
        }
    }

    fun currentPackCards(): List<Card> {
        return when {
            this.packs.isNotEmpty() -> this.packs.first().cards
            else -> return emptyList()
        }
    }

}