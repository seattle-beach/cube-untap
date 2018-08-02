package com.tobert.cube.repositories

import com.tobert.cube.models.DrafterCard
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DrafterCardRepository : JpaRepository <DrafterCard,Long> {
}
