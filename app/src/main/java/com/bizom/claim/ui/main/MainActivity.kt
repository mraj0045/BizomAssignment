package com.bizom.claim.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.bizom.claim.databinding.ActivityMainBinding
import com.bizom.claim.ui.claim.AddClaimActivity
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this, viewModelProviderFactory)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        observerClaimData()
        setUpListeners()
    }

    /**
     * Observes the claim data from the room database & displays it in the recyclerview.
     */
    private fun observerClaimData() {
        val adapter = ClaimDataAdapter()
        binding.rvClaims.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        binding.rvClaims.adapter = adapter
        viewModel.getClaimData().observe(this) { adapter.submitList(it) }
    }

    /**
     * Button Action listeners.
     */
    private fun setUpListeners() {
        binding.btnAddClaim.setOnClickListener {
            startActivity(Intent(this, AddClaimActivity::class.java))
        }
    }

}