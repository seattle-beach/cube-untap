package com.tobert.cube.repositories

import com.tobert.cube.models.Drafter
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DrafterRepository : JpaRepository <Drafter,Long> {
    fun findByName(name: String): Drafter?
}
