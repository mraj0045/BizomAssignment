package com.bizom.claim.ui.main

import com.bizom.claim.db.ClaimData
import com.bizom.claim.model.Claim
import kotlinx.coroutines.flow.Flow

/**
 * Abstract class for the repository.
 */
interface MainRepository {

    /**
     * Returns observable instance of the claim data from the room database.
     *
     * @return
     */
    fun getClaimData(): Flow<List<ClaimData>>

    /**
     * Return the list of claim from the JSON file in assets folder.
     *
     * @return
     */
    fun getClaims(): List<Claim>

    /**
     * Saves the user input data to the room database.
     *
     * @param claimType User selected type.
     * @param map User input data map.
     */
    suspend fun saveClaim(claimType: String, map: Map<String, String>)

}