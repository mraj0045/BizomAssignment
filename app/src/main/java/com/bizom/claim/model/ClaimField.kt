package com.bizom.claim.model


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ClaimField(
    @SerializedName("Claimfieldoption")
    val claimFieldOption: List<ClaimFieldOption>? = null,
    @SerializedName("created")
    val created: String? = null,
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("isdependant")
    val isDependant: String? = null,
    @SerializedName("label")
    val label: String? = null,
    @SerializedName("modified")
    val modified: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("required")
    val required: String? = null,
    @SerializedName("type")
    val type: ClaimFieldType? = null
)