package com.phyothinzaraung.actorapp.repository

import com.phyothinzaraung.actorapp.retrofit.Character
import com.phyothinzaraung.actorapp.retrofit.CharacterApi

class CharacterRepository(private val characterApi: CharacterApi) {

    suspend fun getCharacters(): List<Character>{
        return characterApi.getCharacters()
    }
}