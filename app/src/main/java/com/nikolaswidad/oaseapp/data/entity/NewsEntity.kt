package com.nikolaswidad.oaseapp.data.entity

data class NewsEntity(
    val id: Int,
    val title: String,
    val author: String,
    val timestamp: String,
    val credibilityScore: Int,
    val sentiment: String,
    val summarize: String,
    val url: String,
    val bookmarked: Boolean
)
