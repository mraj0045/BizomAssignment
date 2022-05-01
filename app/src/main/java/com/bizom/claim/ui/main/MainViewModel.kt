package com.bizom.claim.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bizom.claim.model.Claim
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {

    /**
     * Returns observable instance of the claim data from the room database.
     *
     * @return
     */
    fun getClaimData() = repository.getClaimData().asLiveData()

    /**
     * Return the list of claim from the JSON file in assets folder.
     *
     * @return
     */
    fun getClaims(): List<Claim> {
        return repository.getClaims()
    }

    /**
     * Saves the user input data to the room database.
     *
     * @param claimType User selected type.
     * @param map User input data map.
     */
    fun saveClaimData(claimType: String, map: Map<String, String>) {
        CoroutineScope(Dispatchers.IO).launch { repository.saveClaim(claimType, map) }
    }

}