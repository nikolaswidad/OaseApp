package com.nikolaswidad.oaseapp.domain.model

data class Article(
    val id: Int,
    val title: String,
    val image: String,
    val source: String,
    val publishedAt: String,
    val url: String,
    val credibility: Int, // 0 = Fake, 1 = Real
    val credibiliyPercentage: Int,
    val sentiment: Int,// 1 = negative, 2 = neutral, 3 = positive
    val sentimentPercentage: Int,
    val summary: String,
    val bookmarked: Boolean,
)
