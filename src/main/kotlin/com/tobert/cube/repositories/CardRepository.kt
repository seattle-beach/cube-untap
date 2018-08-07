package com.tobert.cube.repositories

import com.tobert.cube.models.Card
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CardRepository : JpaRepository <Card, Int>