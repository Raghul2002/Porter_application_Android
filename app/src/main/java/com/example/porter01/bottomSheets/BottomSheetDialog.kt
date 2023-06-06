package com.example.porter01.bottomSheets
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.porter01.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class BottomSheetDialog : BottomSheetDialogFragment() {
    @SuppressLint("MissingInflatedId")
    companion object {
        const val TAG = "ModalBottomSheet"
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.bottom_sheet_layout,container, false)
        val buttonLocal = view.findViewById<ConstraintLayout>(R.id.button_local)
        val buttonOutStation = view.findViewById<ConstraintLayout>(R.id.button_out_station)
        val buttonRental = view.findViewById<ConstraintLayout>(R.id.button_rental)

        buttonLocal.setOnClickListener {
            Toast.makeText(activity, "Local", Toast.LENGTH_SHORT).show()
            dismiss()
        }
        buttonOutStation.setOnClickListener {
            Toast.makeText(activity, "Out Station", Toast.LENGTH_SHORT).show()
            dismiss()
        }
        buttonRental.setOnClickListener {
            Toast.makeText(activity, "Rental", Toast.LENGTH_SHORT).show()
            dismiss()
        }
        return view
    }
}
