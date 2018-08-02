package com.tobert.cube.configuration

import com.tobert.cube.models.Drafter
import com.tobert.cube.repositories.DrafterRepository
import com.tobert.cube.services.CardService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.context.event.EventListener

@Configuration
class SeedDatabase {
    @Autowired
    lateinit var cardService: CardService

    @EventListener
    fun seed(event: ContextRefreshedEvent) {
        cardService.createCube(listOf(
                "Cryptic Command",
                "Fact or Fiction",
                "Mystic Confluence",
                "Condescend",
                "Dig Through Time",
                "Ancestral Vision",
                "Ponder",
                "Preordain",
                "Chart a Course",
                "Show and Tell",
                "Tinker",
                "Deep Analysis",
                "Bribery",
                "Time Spiral",
                "Upheaval",
                "Treasure Cruise",
                "Search for Azcanta",
                "Control Magic",
                "Opposition",
                "Treachery",
                "Academy Ruins",
                "Faerie Conclave",
                "Shelldock Isle",
                "Tolarian Academy",
                "Vivid Creek",
                "Bloodsoaked Champion",
                "Carnophage",
                "Diregraf Ghoul",
                "Dread Wanderer",
                "Gravecrawler"
        ))
    }
}