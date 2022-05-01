package com.bizom.claim.ui.main

import android.content.Context
import com.bizom.claim.db.AppDatabase
import com.bizom.claim.db.ClaimData
import com.bizom.claim.model.Claim
import com.bizom.claim.utils.toClaimResponse
import kotlinx.coroutines.flow.Flow
import java.io.IOException
import javax.inject.Inject

/**
 * Impl of the [MainRepository] interface.
 *
 * @property appDatabase
 * @property context
 */
class MainRepositoryImpl @Inject constructor(
    private val appDatabase: AppDatabase,
    private val context: Context,
) : MainRepository {

    override fun getClaimData(): Flow<List<ClaimData>> {
        return appDatabase.claimDao().getClaimData()
    }

    override fun getClaims(): List<Claim> {
        val strResponse = try {
            context.assets.open("claims.json").bufferedReader().use { it.readText() }
        } catch (e: IOException) {
            null
        } ?: return emptyList()
        return strResponse.toClaimResponse()?.claims ?: emptyList()
    }

    override suspend fun saveClaim(claimType: String, map: Map<String, String>) {
        appDatabase.claimDao().insert(ClaimData(claimType = claimType, data = map))
    }

}