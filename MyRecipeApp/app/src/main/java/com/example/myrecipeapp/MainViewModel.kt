package com.example.myrecipeapp

import androidx.compose.runtime.RecomposeScope
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel: ViewModel() {
    private val _categoryState = mutableStateOf(RecipeState())
    val categoryState: State<RecipeState> = _categoryState;


    init {
        fetchCategories()
    }

    private fun fetchCategories() {
        viewModelScope.launch {
            try{
                val reponse = recipeService.getCategories()
                _categoryState.value = _categoryState.value.copy(
                    isLoading = false,
                    categories = reponse.categories,
                    errorMessage = null,
                )
            } catch (e: Exception) {
                _categoryState.value = _categoryState.value.copy(
                    isLoading = false,
                    errorMessage = "Error in fetching categoires: ${e.message}"
                )
            }
        }
    }

    data class RecipeState(
        val isLoading: Boolean = true,
        val categories: List<Category> = emptyList(),
        val errorMessage: String? = null,
    )
}