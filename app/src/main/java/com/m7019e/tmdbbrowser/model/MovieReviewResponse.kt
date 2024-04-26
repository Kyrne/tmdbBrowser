package com.m7019e.tmdbbrowser.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieReviewResponse(
    @SerialName(value = "results")
    var reviews: List<Review> = listOf(),
)