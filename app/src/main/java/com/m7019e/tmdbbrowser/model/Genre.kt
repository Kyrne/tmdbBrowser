package com.m7019e.tmdbbrowser.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Genre(
    @SerialName(value = "id")
    var id: Int,

    @SerialName(value = "name")
    var name: String,
)