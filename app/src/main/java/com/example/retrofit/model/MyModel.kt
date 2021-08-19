package com.example.retrofit.model

import com.google.gson.annotations.SerializedName


data class MyModel (
    @SerializedName("id") val id : Int,
    @SerializedName("type") val type : String,
    @SerializedName("setup") val setup : String,
    @SerializedName("punchline") val punchline : String
)
