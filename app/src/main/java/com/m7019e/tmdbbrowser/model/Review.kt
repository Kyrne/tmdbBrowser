package com.m7019e.tmdbbrowser.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Review(
    @SerialName(value = "author")
    var author: String,

    @SerialName(value = "content")
    var content: String,

    @SerialName(value = "created_at")
    var date: String,

    @SerialName(value = "author_details")
    var rating: AuthorDetails
)

@Serializable
data class AuthorDetails(
    @SerialName(value = "rating")
    var rating: Float
)

