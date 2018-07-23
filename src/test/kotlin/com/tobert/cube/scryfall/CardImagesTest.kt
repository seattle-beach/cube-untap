package com.tobert.cube.scryfall

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.readValue
import com.tobert.cube.helpers.DummyCardResponse
import com.tobert.cube.helpers.DummyImages
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CardsResponseTest {
    @Test
    fun `Test single sided Card Response`() {
        val kotlinMapper = ObjectMapper()
        kotlinMapper.registerModule(KotlinModule())
        kotlinMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

        val cardResponse = kotlinMapper.readValue<CardsResponse>("{\n  \"object\": \"card\",\n  \"id\": \"b56b9131-4f7e-4912-ba47-63ed82f21d1b\",\n  \"oracle_id\": \"4b7ac066-e5c7-43e6-9e7e-2739b24a905d\",\n  \"multiverse_ids\": [\n    442921\n  ],\n  \"mtgo_id\": 67531,\n  \"arena_id\": 67170,\n  \"name\": \"Serra Angel\",\n  \"lang\": \"en\",\n  \"uri\": \"https://api.scryfall.com/cards/dom/33\",\n  \"scryfall_uri\": \"https://scryfall.com/card/dom/33?utm_source=api\",\n  \"layout\": \"normal\",\n  \"highres_image\": true,\n  \"image_uris\": {\n    \"small\": \"https://img.scryfall.com/cards/small/en/dom/33.jpg?1524790314\",\n    \"normal\": \"https://img.scryfall.com/cards/normal/en/dom/33.jpg?1524790314\",\n    \"large\": \"https://img.scryfall.com/cards/large/en/dom/33.jpg?1524790314\",\n    \"png\": \"https://img.scryfall.com/cards/png/en/dom/33.png?1524790314\",\n    \"art_crop\": \"https://img.scryfall.com/cards/art_crop/en/dom/33.jpg?1524790314\",\n    \"border_crop\": \"https://img.scryfall.com/cards/border_crop/en/dom/33.jpg?1524790314\"\n  },\n  \"mana_cost\": \"{3}{W}{W}\",\n  \"cmc\": 5,\n  \"type_line\": \"Creature — Angel\",\n  \"oracle_text\": \"Flying, vigilance\",\n  \"power\": \"4\",\n  \"toughness\": \"4\",\n  \"colors\": [\n    \"W\"\n  ],\n  \"color_identity\": [\n    \"W\"\n  ],\n  \"legalities\": {\n    \"standard\": \"legal\",\n    \"future\": \"legal\",\n    \"frontier\": \"legal\",\n    \"modern\": \"legal\",\n    \"legacy\": \"legal\",\n    \"pauper\": \"not_legal\",\n    \"vintage\": \"legal\",\n    \"penny\": \"legal\",\n    \"commander\": \"legal\",\n    \"1v1\": \"legal\",\n    \"duel\": \"legal\",\n    \"brawl\": \"legal\"\n  },\n  \"reserved\": false,\n  \"foil\": true,\n  \"nonfoil\": true,\n  \"oversized\": false,\n  \"reprint\": true,\n  \"set\": \"dom\",\n  \"set_name\": \"Dominaria\",\n  \"set_uri\": \"https://api.scryfall.com/sets/dom\",\n  \"set_search_uri\": \"https://api.scryfall.com/cards/search?order=set&q=e%3Adom&unique=prints\",\n  \"scryfall_set_uri\": \"https://scryfall.com/sets/dom?utm_source=api\",\n  \"rulings_uri\": \"https://api.scryfall.com/cards/dom/33/rulings\",\n  \"prints_search_uri\": \"https://api.scryfall.com/cards/search?order=set&q=%21%E2%80%9CSerra+Angel%E2%80%9D&unique=prints\",\n  \"collector_number\": \"33\",\n  \"digital\": false,\n  \"rarity\": \"uncommon\",\n  \"flavor_text\": \"The angel remembers her past lives like dreams. Her song held up meadows. Her blade drove back darkness. Her wings carried her across the ages.\",\n  \"illustration_id\": \"7aa16de3-4ff9-4ed9-9d62-62c140599508\",\n  \"artist\": \"Donato Giancola\",\n  \"frame\": \"2015\",\n  \"full_art\": false,\n  \"border_color\": \"black\",\n  \"timeshifted\": false,\n  \"colorshifted\": false,\n  \"futureshifted\": false,\n  \"edhrec_rank\": 4122,\n  \"usd\": \"0.03\",\n  \"tix\": \"0.01\",\n  \"eur\": \"0.05\",\n  \"related_uris\": {\n    \"gatherer\": \"http://gatherer.wizards.com/Pages/Card/Details.aspx?multiverseid=442921\",\n    \"tcgplayer_decks\": \"http://decks.tcgplayer.com/magic/deck/search?contains=Serra+Angel&page=1&partner=Scryfall\",\n    \"edhrec\": \"http://edhrec.com/route/?cc=Serra+Angel\",\n    \"mtgtop8\": \"http://mtgtop8.com/search?MD_check=1&SB_check=1&cards=Serra+Angel\"\n  },\n  \"purchase_uris\": {\n    \"amazon\": \"https://www.amazon.com/gp/search?ie=UTF8&index=toys-and-games&keywords=Serra+Angel&tag=scryfall-20\",\n    \"ebay\": \"http://rover.ebay.com/rover/1/711-53200-19255-0/1?campid=5337966903&icep_catId=19107&icep_ff3=10&icep_sortBy=12&icep_uq=Serra+Angel&icep_vectorid=229466&ipn=psmain&kw=lg&kwid=902099&mtid=824&pub=5575230669&toolid=10001\",\n    \"tcgplayer\": \"https://scryfall.com/s/tcgplayer/164227\",\n    \"magiccardmarket\": \"https://scryfall.com/s/mcm/327171\",\n    \"cardhoarder\": \"https://www.cardhoarder.com/cards/67531?affiliate_id=scryfall&ref=card-profile&utm_campaign=affiliate&utm_medium=card&utm_source=scryfall\",\n    \"card_kingdom\": \"https://www.cardkingdom.com/catalog/item/217752?partner=scryfall&utm_campaign=affiliate&utm_medium=scryfall&utm_source=scryfall\",\n    \"mtgo_traders\": \"http://www.mtgotraders.com/deck/ref.php?id=67531&referral=scryfall\",\n    \"coolstuffinc\": \"https://scryfall.com/s/coolstuffinc/4367964\"\n  }\n}")
        assertNotNull(cardResponse.image_uris)
        assertNull(cardResponse.card_faces)
        assertEquals("https://img.scryfall.com/cards/large/en/dom/33.jpg?1524790314", cardResponse.image_uris!!.large)
    }

    @Test
    fun `Test double sided Card Response`() {
        val kotlinMapper = ObjectMapper()
        kotlinMapper.registerModule(KotlinModule())
        kotlinMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

        val cardResponse = kotlinMapper.readValue<CardsResponse>("{\n  \"object\": \"card\",\n  \"id\": \"58c39df6-b237-40d1-bdcb-2fe5d05392a9\",\n  \"oracle_id\": \"e49d745c-5130-4b54-bcbb-643b08528456\",\n  \"multiverse_ids\": [\n    398428,\n    398429\n  ],\n  \"mtgo_id\": 57876,\n  \"mtgo_foil_id\": 57877,\n  \"name\": \"Kytheon, Hero of Akros // Gideon, Battle-Forged\",\n  \"lang\": \"en\",\n  \"uri\": \"https://api.scryfall.com/cards/ori/23\",\n  \"scryfall_uri\": \"https://scryfall.com/card/ori/23/kytheon-hero-of-akros-%2F%2F-gideon-battle-forged?utm_source=api\",\n  \"layout\": \"transform\",\n  \"highres_image\": true,\n  \"cmc\": 1,\n  \"type_line\": \"Legendary Creature — Human Soldier // Legendary Planeswalker — Gideon\",\n  \"color_identity\": [\n    \"W\"\n  ],\n  \"card_faces\": [\n    {\n      \"object\": \"card_face\",\n      \"name\": \"Kytheon, Hero of Akros\",\n      \"mana_cost\": \"{W}\",\n      \"type_line\": \"Legendary Creature — Human Soldier\",\n      \"printed_type_line\": \"\",\n      \"oracle_text\": \"At end of combat, if Kytheon, Hero of Akros and at least two other creatures attacked this combat, exile Kytheon, then return him to the battlefield transformed under his owner's control.\\n{2}{W}: Kytheon gains indestructible until end of turn.\",\n      \"printed_text\": \"\",\n      \"colors\": [\n        \"W\"\n      ],\n      \"power\": \"2\",\n      \"toughness\": \"1\",\n      \"illustration_id\": \"abc512f2-9ddf-46cd-975b-69b0ff395851\",\n      \"image_uris\": {\n        \"small\": \"https://img.scryfall.com/cards/small/en/ori/23a.jpg?1518205331\",\n        \"normal\": \"https://img.scryfall.com/cards/normal/en/ori/23a.jpg?1518205331\",\n        \"large\": \"https://img.scryfall.com/cards/large/en/ori/23a.jpg?1518205331\",\n        \"png\": \"https://img.scryfall.com/cards/png/en/ori/23a.png?1518205331\",\n        \"art_crop\": \"https://img.scryfall.com/cards/art_crop/en/ori/23a.jpg?1518205331\",\n        \"border_crop\": \"https://img.scryfall.com/cards/border_crop/en/ori/23a.jpg?1518205331\"\n      }\n    },\n    {\n      \"object\": \"card_face\",\n      \"name\": \"Gideon, Battle-Forged\",\n      \"mana_cost\": \"\",\n      \"type_line\": \"Legendary Planeswalker — Gideon\",\n      \"printed_type_line\": \"\",\n      \"oracle_text\": \"+2: Up to one target creature an opponent controls attacks Gideon, Battle-Forged during its controller's next turn if able.\\n+1: Until your next turn, target creature gains indestructible. Untap that creature.\\n0: Until end of turn, Gideon, Battle-Forged becomes a 4/4 Human Soldier creature with indestructible that's still a planeswalker. Prevent all damage that would be dealt to him this turn.\",\n      \"printed_text\": \"\",\n      \"colors\": [\n        \"W\"\n      ],\n      \"color_indicator\": [\n        \"W\"\n      ],\n      \"loyalty\": \"3\",\n      \"illustration_id\": \"e57a3c5b-2b82-475e-a8b8-92d05d60962b\",\n      \"image_uris\": {\n        \"small\": \"https://img.scryfall.com/cards/small/en/ori/23b.jpg?1518205331\",\n        \"normal\": \"https://img.scryfall.com/cards/normal/en/ori/23b.jpg?1518205331\",\n        \"large\": \"https://img.scryfall.com/cards/large/en/ori/23b.jpg?1518205331\",\n        \"png\": \"https://img.scryfall.com/cards/png/en/ori/23b.png?1518205331\",\n        \"art_crop\": \"https://img.scryfall.com/cards/art_crop/en/ori/23b.jpg?1518205331\",\n        \"border_crop\": \"https://img.scryfall.com/cards/border_crop/en/ori/23b.jpg?1518205331\"\n      }\n    }\n  ],\n  \"legalities\": {\n    \"standard\": \"not_legal\",\n    \"future\": \"not_legal\",\n    \"frontier\": \"legal\",\n    \"modern\": \"legal\",\n    \"legacy\": \"legal\",\n    \"pauper\": \"not_legal\",\n    \"vintage\": \"legal\",\n    \"penny\": \"not_legal\",\n    \"commander\": \"legal\",\n    \"1v1\": \"legal\",\n    \"duel\": \"legal\",\n    \"brawl\": \"not_legal\"\n  },\n  \"reserved\": false,\n  \"foil\": true,\n  \"nonfoil\": true,\n  \"oversized\": false,\n  \"reprint\": false,\n  \"set\": \"ori\",\n  \"set_name\": \"Magic Origins\",\n  \"set_uri\": \"https://api.scryfall.com/sets/ori\",\n  \"set_search_uri\": \"https://api.scryfall.com/cards/search?order=set&q=e%3Aori&unique=prints\",\n  \"scryfall_set_uri\": \"https://scryfall.com/sets/ori?utm_source=api\",\n  \"rulings_uri\": \"https://api.scryfall.com/cards/ori/23/rulings\",\n  \"prints_search_uri\": \"https://api.scryfall.com/cards/search?order=set&q=%21%E2%80%9CKytheon%2C+Hero+of+Akros+%2F%2F+Gideon%2C+Battle-Forged%E2%80%9D&unique=prints\",\n  \"collector_number\": \"23\",\n  \"digital\": false,\n  \"rarity\": \"mythic\",\n  \"artist\": \"Willian Murai\",\n  \"frame\": \"2015\",\n  \"full_art\": false,\n  \"border_color\": \"black\",\n  \"timeshifted\": false,\n  \"colorshifted\": false,\n  \"futureshifted\": false,\n  \"edhrec_rank\": 4257,\n  \"usd\": \"3.51\",\n  \"tix\": \"0.91\",\n  \"eur\": \"3.19\",\n  \"related_uris\": {\n    \"gatherer\": \"http://gatherer.wizards.com/Pages/Card/Details.aspx?multiverseid=398428\",\n    \"tcgplayer_decks\": \"http://decks.tcgplayer.com/magic/deck/search?contains=Kytheon%2C+Hero+of+Akros&page=1&partner=Scryfall\",\n    \"edhrec\": \"http://edhrec.com/route/?cc=Kytheon%2C+Hero+of+Akros\",\n    \"mtgtop8\": \"http://mtgtop8.com/search?MD_check=1&SB_check=1&cards=Kytheon%2C+Hero+of+Akros\"\n  },\n  \"purchase_uris\": {\n    \"amazon\": \"https://www.amazon.com/gp/search?ie=UTF8&index=toys-and-games&keywords=Kytheon%2C+Hero+of+Akros&tag=scryfall-20\",\n    \"ebay\": \"http://rover.ebay.com/rover/1/711-53200-19255-0/1?campid=5337966903&icep_catId=19107&icep_ff3=10&icep_sortBy=12&icep_uq=Kytheon%2C+Hero+of+Akros&icep_vectorid=229466&ipn=psmain&kw=lg&kwid=902099&mtid=824&pub=5575230669&toolid=10001\",\n    \"tcgplayer\": \"https://scryfall.com/s/tcgplayer/99958\",\n    \"magiccardmarket\": \"https://scryfall.com/s/mcm/283342\",\n    \"cardhoarder\": \"https://www.cardhoarder.com/cards/57876?affiliate_id=scryfall&ref=card-profile&utm_campaign=affiliate&utm_medium=card&utm_source=scryfall\",\n    \"card_kingdom\": \"https://www.cardkingdom.com/catalog/item/201589?partner=scryfall&utm_campaign=affiliate&utm_medium=scryfall&utm_source=scryfall\",\n    \"mtgo_traders\": \"http://www.mtgotraders.com/deck/ref.php?id=57876&referral=scryfall\",\n    \"coolstuffinc\": \"https://scryfall.com/s/coolstuffinc/2773063\"\n  }\n}")
        assertNotNull(cardResponse.card_faces)
        assertNull(cardResponse.image_uris)
        assertEquals("https://img.scryfall.com/cards/large/en/ori/23a.jpg?1518205331", cardResponse.card_faces!![0].image_uris!!.large)
    }

    @Test
    fun `Get image for single sided card response`() {
        val images = DummyImages()
        val cardResponse = DummyCardResponse(image_uris = images)

        assertEquals(images, cardResponse.images())
    }

    @Test
    fun `Get image for double sided card response`() {
        val images = DummyImages()

        val cardResponse = DummyCardResponse(
                card_faces = listOf(
                        DummyCardResponse(image_uris = images)
                )
        )

        assertEquals(images, cardResponse.images())
    }

    @Test
    fun `Handle missing images in card response`() {
        val cardResponse = DummyCardResponse()

        assertNotNull(cardResponse.images())
        assertEquals(CardImages(
                small = "",
                normal = "",
                large = "",
                png = "",
                art_crop = "",
                border_crop = ""
        ), cardResponse.images())
    }
}


