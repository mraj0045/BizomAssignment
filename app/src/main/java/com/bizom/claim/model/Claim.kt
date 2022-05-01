package com.bizom.claim.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Claim(
    @SerializedName("Claimtype")
    val claimType: ClaimType? = null,
    @SerializedName("Claimtypedetail")
    val claimTypeDetail: List<ClaimTypeDetail>? = null
)