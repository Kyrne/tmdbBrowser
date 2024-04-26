package com.m7019e.tmdbbrowser.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VideosResponse(
    @SerialName(value = "results")
    var results: List<Video> = listOf()
)

@Serializable
data class Video(
    @SerialName(value = "name")
    var name: String,

    @SerialName(value = "type")
    var type: String,

    @SerialName(value = "key")
    var key: String,

    @SerialName(value = "site")
    var site: String
)
