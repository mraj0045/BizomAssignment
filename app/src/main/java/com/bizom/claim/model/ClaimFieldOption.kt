package com.bizom.claim.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class ClaimFieldOption(
    @SerializedName("belongsto")
    val belongsto: Any? = null,
    @SerializedName("claimfield_id")
    val claimfieldId: String? = null,
    @SerializedName("hasmany")
    val hasmany: String? = null,
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("label")
    val label: String? = null,
    @SerializedName("name")
    val name: String? = null
)