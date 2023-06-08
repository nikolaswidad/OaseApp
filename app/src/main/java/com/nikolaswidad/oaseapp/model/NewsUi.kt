package com.nikolaswidad.oaseapp.model

data class NewsUi(
    val id: Long,
    val title: String,
    val image: String,
    val source: String,
    val publishedAt: String,
    val verified: String,
    val verifiedPercentage: Int,
    val sentiment: String,
    val sentimentPercentage: Int,
    val summary: String,
    val bookmarked: Boolean,
)
