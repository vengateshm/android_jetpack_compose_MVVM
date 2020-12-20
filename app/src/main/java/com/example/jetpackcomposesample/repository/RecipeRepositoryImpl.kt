package com.example.jetpackcomposesample.repository

import com.example.jetpackcomposesample.domain.model.Recipe
import com.example.jetpackcomposesample.network.RecipeService
import com.example.jetpackcomposesample.network.model.RecipeDtoMapper

class RecipeRepositoryImpl(
    private val recipeService: RecipeService,
    private val mapper: RecipeDtoMapper
) : RecipeRepository {
    override suspend fun search(token: String, page: Int, query: String): List<Recipe> =
        mapper.toDomainList(recipeService.search(token, page, query).recipes)

    override suspend fun get(token: String, id: Int): Recipe =
        mapper.mapToDomainModel(recipeService.get(token, id))
}