package ru.serget.myplacesonmap.view.screens.myplaces

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputEditText
import ru.serget.myplacesonmap.R
import ru.serget.myplacesonmap.databinding.EditPlaceDialogFragmentBinding
import ru.serget.myplacesonmap.model.data.ItemPlace

class EditPlaceDialogFragment(private val itemPlace: ItemPlace) : BottomSheetDialogFragment() {

    private lateinit var binding: EditPlaceDialogFragmentBinding
    private lateinit var titleEditText: TextInputEditText
    private lateinit var descriptionEditText: TextInputEditText
    private lateinit var clearTitleImageView: ImageView
    private lateinit var clearDescriptionImageView: ImageView
    private lateinit var saveButton: TextView
    private var onSaveClickListener: OnSaveClickListener? = null

    private fun textWatcher(clearButton: ImageView, editText: TextInputEditText) = object : TextWatcher {

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            if (editText.text != null && editText.text.toString().isNotEmpty()) {
                saveButton.isEnabled = true
                clearButton.visibility = View.VISIBLE
            } else {
                saveButton.isEnabled = false
                clearButton.visibility = View.GONE
            }
        }
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun afterTextChanged(s: Editable) {}
    }

    private val onSaveButtonClickListener =
        View.OnClickListener {
            onSaveClickListener?.onClick(titleEditText.text.toString(), descriptionEditText.text.toString())
            dismiss()
        }

    internal fun setOnSaveClickListener(listener: OnSaveClickListener) {
        onSaveClickListener = listener
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.edit_place_dialog_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = EditPlaceDialogFragmentBinding.bind(view)
        titleEditText = binding.titleEditText
        descriptionEditText = binding.descriptionEditText
        clearTitleImageView = binding.clearTitleTextImageview
        clearDescriptionImageView = binding.clearDescriptionTextImageview
        saveButton = binding.saveButtonTextview

        saveButton.setOnClickListener(onSaveButtonClickListener)
        titleEditText.addTextChangedListener(textWatcher(clearButton = clearTitleImageView, editText = titleEditText))
        descriptionEditText.addTextChangedListener(textWatcher(clearButton = clearDescriptionImageView, editText = descriptionEditText))
        addOnClearClickListener(clearButton = clearTitleImageView, editText = titleEditText)
        addOnClearClickListener(clearButton = clearDescriptionImageView, editText = descriptionEditText)

        titleEditText.setText(itemPlace.title)
        descriptionEditText.setText(itemPlace.description)
    }

    override fun onDestroyView() {
        onSaveClickListener = null
        super.onDestroyView()
    }

    private fun addOnClearClickListener(clearButton: ImageView, editText: TextInputEditText) {
        clearButton.setOnClickListener {
            editText.text?.clear()
            saveButton.isEnabled = false
        }
    }

    interface OnSaveClickListener {
        fun onClick(titlePlace: String, descriptionPlace: String)
    }

    companion object {
        fun newInstance(itemPlace: ItemPlace): EditPlaceDialogFragment {
            return EditPlaceDialogFragment(itemPlace)
        }
    }
}
