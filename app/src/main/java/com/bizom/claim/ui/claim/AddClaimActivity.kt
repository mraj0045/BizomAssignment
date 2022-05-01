package com.bizom.claim.ui.claim

import android.os.Bundle
import android.text.InputType
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.widget.AppCompatSpinner
import androidx.lifecycle.ViewModelProvider
import com.bizom.claim.R
import com.bizom.claim.databinding.ActivityAddClaimBinding
import com.bizom.claim.databinding.LayoutInputBinding
import com.bizom.claim.databinding.LayoutSpinnerFieldBinding
import com.bizom.claim.databinding.LayoutTitleLabelBinding
import com.bizom.claim.model.Claim
import com.bizom.claim.model.ClaimFieldType
import com.bizom.claim.model.ClaimTypeDetail
import com.bizom.claim.ui.main.MainViewModel
import com.bizom.claim.utils.toPString
import com.google.android.material.textfield.TextInputLayout
import dagger.android.support.DaggerAppCompatActivity
import java.util.*
import javax.inject.Inject

class AddClaimActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProvider.Factory
    private val binding: ActivityAddClaimBinding by lazy { ActivityAddClaimBinding.inflate(layoutInflater) }
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this, viewModelProviderFactory)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setUpClaimTypeAdapter()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add_claim, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_save) {
            parseDataFromView()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    /**
     * Create an instance of [TextInputLayout] based on the [detail] param & returns a [TextInputLayout]
     *
     * @param detail [ClaimTypeDetail] object from the selected claim type.
     * @param viewGroup Parent ViewGroup in which the view will be added.
     * @param isAllCaps true for all caps text, false otherwise.
     * @param isNumeric true if the data is numeric, false otherwise.
     * @return
     */
    private fun createInputField(
        detail: ClaimTypeDetail,
        viewGroup: ViewGroup,
        isAllCaps: Boolean,
        isNumeric: Boolean
    ): TextInputLayout {
        val inputLayout = LayoutInputBinding.inflate(layoutInflater, viewGroup, false).root
        inputLayout.id = detail.claimFieldId
        inputLayout.hint = detail.claimField?.label
        inputLayout.editText?.inputType =
            when {
                isNumeric -> InputType.TYPE_CLASS_NUMBER
                isAllCaps -> InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS
                else -> InputType.TYPE_CLASS_TEXT
            }
        return inputLayout
    }

    /**
     * Creates a Spinner instance, populates it with data from the [detail] param & returns it.
     *
     * @param detail [ClaimTypeDetail] object from the selected claim type.
     * @param parent [AppCompatSpinner] instance.
     * @return
     */
    private fun createSpinner(detail: ClaimTypeDetail, parent: ViewGroup): AppCompatSpinner? {
        val options = detail.claimField?.claimFieldOption ?: return null
        val label = detail.claimField.label
        createTitleLabel(label, parent)
        return LayoutSpinnerFieldBinding.inflate(layoutInflater, parent, false).root.apply {
            id = detail.claimFieldId
            prompt = label ?: ""
            this.adapter = ArrayAdapter<String>(
                this@AddClaimActivity, R.layout.item_claim_type, R.id.txtClaimTitle, options.map { it.name })
        }
    }

    /**
     * Creates new instance of the [TextView] & add it to view hierarchy as the title label.
     *
     * @param title Title text to display in the TextView.
     * @param viewGroup
     */
    private fun createTitleLabel(title: String?, viewGroup: ViewGroup) {
        if (title == null) return
        val labelView = LayoutTitleLabelBinding.inflate(layoutInflater, viewGroup, false).root
        labelView.text = title
        viewGroup.addView(labelView)
    }

    /**
     * Fetches the data entered by the user & saves it in the local storage.
     */
    private fun parseDataFromView() {
        val fieldIdList = (binding.spinnerClaim.selectedItem as Claim?)?.claimTypeDetail
        if (fieldIdList.isNullOrEmpty()) return
        val map = mutableMapOf<String, String>()
        fieldIdList.forEach { detail ->
            val value = when (val view = findViewById<View>(detail.claimFieldId)) {
                is AppCompatSpinner -> view.selectedItem.toString()
                is TextInputLayout -> view.editText?.text?.toString()?.takeIf { it.isNotEmpty() }
                else -> null
            }
            detail.claimField?.label?.let { key -> value?.let { map[key] = it } }
        }
        map["Raised at"] = Date().toPString()
        if (map.isNotEmpty()) {
            (binding.spinnerClaim.selectedItem as Claim?)?.claimType?.name?.let {
                viewModel.saveClaimData(it, map)
            }
            finish()
        }
    }

    /**
     * Fetches claim data from the JSON file in the assets & load them in the spinner.
     */
    private fun setUpClaimTypeAdapter() {
        val adapter = ClaimTypeAdapter(this, viewModel.getClaims().toMutableList())
        binding.spinnerClaim.adapter = adapter
        binding.spinnerClaim.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                val claim = adapter.getItem(position)
                updateViews(claim)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                // do nothing
            }
        }
    }

    /**
     * Updates the Views based on the input fields required for the selected [claim]
     *
     * @param claim User selected claim
     */
    private fun updateViews(claim: Claim?) {
        val ll = binding.viewLayout
        for (i in ll.childCount - 1 downTo 2) {
            ll.removeViewAt(i)
        }
        if (claim == null) {
            return
        }
        val detail = claim.claimTypeDetail ?: return

        for (i in detail) {
            val view = when (i.claimField?.type) {
                ClaimFieldType.DropDown -> createSpinner(i, ll)
                ClaimFieldType.SingleLineText ->
                    createInputField(i, ll, isAllCaps = false, isNumeric = false)
                ClaimFieldType.SingleLineTextAllCaps ->
                    createInputField(i, ll, isAllCaps = true, isNumeric = false)
                ClaimFieldType.SingleLineTextNumeric ->
                    createInputField(i, ll, isAllCaps = false, isNumeric = true)
                else -> null
            }
            view?.run { ll.addView(this) }
        }
    }


}