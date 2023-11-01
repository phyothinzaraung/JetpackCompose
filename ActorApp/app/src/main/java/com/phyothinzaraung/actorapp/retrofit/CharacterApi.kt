package com.phyothinzaraung.actorapp.retrofit

import com.phyothinzaraung.actorapp.retrofit.Character
import retrofit2.http.GET

interface CharacterApi {

    @GET("characters")
    suspend fun getCharacters(): List<Character>
}