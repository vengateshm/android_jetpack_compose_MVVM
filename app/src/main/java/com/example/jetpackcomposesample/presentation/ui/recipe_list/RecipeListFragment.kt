package com.example.jetpackcomposesample.presentation.ui.recipe_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.jetpackcomposesample.presentation.components.CircularIndeterminateProgressBar
import com.example.jetpackcomposesample.presentation.components.RecipeCard
import com.example.jetpackcomposesample.presentation.components.SearchAppBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeListFragment : Fragment() {

    private val viewModel: RecipeListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                val recipes = viewModel.recipes.value
                val query = viewModel.query.value
                val selectedCategory = viewModel.selectedCategory.value
                val loading = viewModel.loading.value
                Column {
                    SearchAppBar(
                        query = query,
                        onQueryChanged = viewModel::onQueryChanged,
                        onExecuteSearch = viewModel::newSearch,
                        scrollPosition = viewModel.categoryScrollPosition,
                        categories = getAllFoodCategories(),
                        selectedCategory = selectedCategory,
                        onSelectedCategoryChanged = viewModel::onSelectedCategoryChanged,
                        onChangeCategoryScrollPosition = viewModel::onChangeCategoryScrollPosition,
                    )

                    Box(modifier = Modifier.fillMaxSize()) {
                        LazyColumn {
                            itemsIndexed(
                                items = recipes
                            ) { index, recipe ->
                                RecipeCard(recipe, onClick = {})
                            }
                        }
                        CircularIndeterminateProgressBar(isDisplayed = loading)
                    }
                }
            }
        }
    }
}