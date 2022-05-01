package com.bizom.claim.utils

import com.bizom.claim.model.ClaimResponse
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.util.*


fun String?.toClaimResponse(): ClaimResponse? {
    if (this == null) return null
    return GsonBuilder().create().fromJson(this, ClaimResponse::class.java)
}

fun Date.toPString(): String {
    val sdf = SimpleDateFormat("dd/MMM/yyyy hh:mm a", Locale.ENGLISH)
    return sdf.format(this)
}

/**
 * Returns the [Type] of the class
 *
 * @param T
 * @return
 */
inline fun <reified T> toTypeToken(): Type {
    return object : TypeToken<T>() {}.type
}
