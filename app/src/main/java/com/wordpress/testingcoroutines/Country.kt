package com.wordpress.testingcoroutines

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Country(@SerializedName("Country") val country: String,
                   @PrimaryKey @SerializedName("Slug") val slug: String,
                   @SerializedName("ISO2") val iso: String)