package com.bizom.claim.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class ClaimResponse(
    @SerializedName("Claims")
    val claims: List<Claim>? = null,
    @SerializedName("Reason")
    val reason: String? = null,
    @SerializedName("Result")
    val result: Boolean? = null
)