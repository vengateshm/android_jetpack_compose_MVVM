package com.example.jetpackcomposesample.presentation.ui.recipe_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcomposesample.domain.model.Recipe
import com.example.jetpackcomposesample.repository.RecipeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Named

class RecipeListViewModel @ViewModelInject constructor(
    private val repository: RecipeRepository,
    @Named("auth_token")
    private val token: String
) : ViewModel() {

    val recipes: MutableState<List<Recipe>> = mutableStateOf(ArrayList())
    val query: MutableState<String> = mutableStateOf("")
    val selectedCategory: MutableState<FoodCategory?> = mutableStateOf(null)
    var categoryScrollPosition = 0f
    val loading: MutableState<Boolean> = mutableStateOf(false)

    init {
        //newSearch()
    }

    fun newSearch() {
        viewModelScope.launch(Dispatchers.IO) {
            loading.value = true
            resetSearchState()
            val result = repository.search(
                token = token,
                page = 1,
                query = query.value
            )
            recipes.value = result
            loading.value = false
        }
    }

    private fun resetSearchState() {
        recipes.value = listOf()
        if (selectedCategory.value?.value != query.value) {
            clearSelectedCategory()
        }
    }

    private fun clearSelectedCategory() {
        selectedCategory.value = null
    }

    fun onQueryChanged(query: String) {
        this.query.value = query
    }

    fun onSelectedCategoryChanged(category: String) {
        val newCategory = getFoodCategory(category)
        selectedCategory.value = newCategory
        onQueryChanged(category)
    }

    fun onChangeCategoryScrollPosition(position: Float) {
        categoryScrollPosition = position
    }
}