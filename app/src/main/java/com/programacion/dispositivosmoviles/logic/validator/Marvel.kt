package com.programacion.dispositivosmoviles.logic.validator

import com.programacion.dispositivosmoviles.data.entities.Superheroes

class Marvel {

    fun returnMarvelChar(): List<Superheroes> {
        val listaHeroes = listOf(
            Superheroes(
                1,
                "Spiderman",
                "The Amazing Spiderman",
                "https://comicvine.gamespot.com/a/uploads/scale_small/12/124259/8126579-amazing_spider-man_vol_5_54_stormbreakers_variant_textless.jpg"
            ),
            Superheroes(
                2,
                "Hulk",
                "The Incredible Hulk",
                "https://comicvine.gamespot.com/a/uploads/scale_small/12/124259/7892286-immortal_hulk_vol_1_38_.jpg"
            ),
            Superheroes(
                3,
                "Black Panther",
                "The Avengers",
                "https://comicvine.gamespot.com/a/uploads/scale_small/12/124259/8251800-black_panther_vol_8_1_devil_dog_comics_and_jolzar_collectibles_exclusive_virgin_variant.jpg"
            ),
            Superheroes(
                4,
                "Invisible Woman",
                "Fantastic Four",
                "https://comicvine.gamespot.com/a/uploads/scale_small/11141/111413247/7267710-4df69cb3-7b54-480e-89e5-2c7e68c52b1b_rw_1200.jpg"
            ),
            Superheroes(
                5,
                "Doctor Strange",
                "The Defenders",
                "https://comicvine.gamespot.com/a/uploads/scale_small/6/68065/8768812-8651456135-docto.jpg"
            )
        )
        return listaHeroes
    }
}