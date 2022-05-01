package com.bizom.claim.ui.claim

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.bizom.claim.R
import com.bizom.claim.databinding.ItemClaimTypeBinding
import com.bizom.claim.model.Claim

/**
 * ArrayAdapter implementation to display the claim types from the JSON file.
 *
 * @constructor
 *
 * @param context Activity context
 * @param objects List of Claim objects from the JSON file.
 */
class ClaimTypeAdapter(context: Context, objects: MutableList<Claim>) :
    ArrayAdapter<Claim>(context, R.layout.item_claim_type, R.id.txtClaimTitle, objects) {

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createView(position, convertView, parent)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createView(position, convertView, parent)
    }

    private fun createView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = if (convertView == null)
            ItemClaimTypeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        else ItemClaimTypeBinding.bind(convertView)

        val claim = getItem(position)
        binding.txtClaimTitle.text = claim?.claimType?.name
        return binding.root
    }
}