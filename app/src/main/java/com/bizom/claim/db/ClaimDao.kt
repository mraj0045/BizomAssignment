package com.bizom.claim.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ClaimDao {

    /**
     * Returns observable instance of the claim data
     *
     * @return
     */
    @Query("SELECT * FROM ClaimData")
    fun getClaimData(): Flow<List<ClaimData>>

    /**
     * Saves the user data.
     *
     * @param claimData
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(claimData: ClaimData)

}
