package com.bizom.claim.db

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Room database Entity class.
 *
 * @property id
 * @property claimType
 * @property data
 */
@Entity
data class ClaimData(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val claimType: String = "",
    val data: Map<String, String> = emptyMap()
)
