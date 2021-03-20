package com.jmarkstar.gumtree_challenge.repositories.entities

import com.google.gson.annotations.SerializedName

data class Weather(
    @SerializedName("id") val conditionId: Int,
    @SerializedName("main") val generalName: String,
    val description: String,
    @SerializedName("icon") val iconName: String
)
