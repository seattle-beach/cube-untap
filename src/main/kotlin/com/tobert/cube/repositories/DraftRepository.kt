package com.tobert.cube.repositories

import com.tobert.cube.models.Draft
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DraftRepository : JpaRepository<Draft, Long>