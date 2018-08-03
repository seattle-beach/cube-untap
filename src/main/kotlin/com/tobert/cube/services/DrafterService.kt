package com.tobert.cube.services

import com.tobert.cube.models.Drafter
import com.tobert.cube.repositories.DrafterRepository
import org.springframework.stereotype.Service

@Service
class DrafterService(val drafterRepository: DrafterRepository) {
    fun getAllShuffled(): List<Drafter> {
        return drafterRepository.findAll().shuffled()
    }

    fun save(drafter: Drafter) {
        drafterRepository.save(drafter)
    }
}
