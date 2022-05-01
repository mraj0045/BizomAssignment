package com.bizom.claim.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class ClaimType(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("name")
    val name: String? = null
)