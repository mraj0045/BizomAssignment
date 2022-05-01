package com.bizom.claim.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bizom.claim.databinding.ItemClaimDataBinding
import com.bizom.claim.db.ClaimData

/**
 * RecyclerView adapter to show the list of claims added by the user.
 */
class ClaimDataAdapter : ListAdapter<ClaimData, ClaimDataAdapter.ClaimDataHolder>(ClaimDataDiff) {


    override fun onBindViewHolder(holder: ClaimDataHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClaimDataHolder {
        return ClaimDataHolder(
            ItemClaimDataBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    inner class ClaimDataHolder(private val binding: ItemClaimDataBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ClaimData?) {
            if (item == null) return
            binding.txtClaimTitle.text = item.claimType
            binding.txtClaimData.text =
                item.data.map { "${it.key} : ${it.value}" }.joinToString(separator = "\n") { it }
        }
    }

    /**
     * Item diff impl to update the adapter efficiently.
     */
    object ClaimDataDiff : DiffUtil.ItemCallback<ClaimData>() {

        override fun areItemsTheSame(oldItem: ClaimData, newItem: ClaimData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ClaimData, newItem: ClaimData): Boolean {
            return oldItem == newItem
        }

    }

}