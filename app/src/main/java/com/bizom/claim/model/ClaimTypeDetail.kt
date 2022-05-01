package com.bizom.claim.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class ClaimTypeDetail(
    @SerializedName("Claimfield")
    val claimField: ClaimField? = null,
    @SerializedName("claimfield_id")
    val claimFieldId: Int = 0,
    @SerializedName("claimtype_id")
    val claimTypeId: String? = null,
    @SerializedName("id")
    val id: String? = null
)