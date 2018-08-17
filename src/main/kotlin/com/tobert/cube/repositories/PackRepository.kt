package com.tobert.cube.repositories

import com.tobert.cube.models.Pack
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PackRepository : JpaRepository<Pack, Int>