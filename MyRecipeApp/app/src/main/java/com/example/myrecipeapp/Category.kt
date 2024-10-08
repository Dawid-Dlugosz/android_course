package com.example.myrecipeapp

data class Category(
    val idCategory: String,
    val strCategory: String,
    val strCategoryThumb: String,
    val strCategoryDeclaration: String
)

data class CategoriesResponse(
    val categories: List<Category>
)
