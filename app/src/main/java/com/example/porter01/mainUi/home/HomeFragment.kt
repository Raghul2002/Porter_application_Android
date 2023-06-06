package com.example.porter01.mainUi.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.GridView
import android.widget.Toast
import com.example.porter01.mainUi.home.gridView.Vehicle
import com.example.porter01.bottomSheets.BottomSheetDialog
import com.example.porter01.databinding.FragmentHomeBinding
import com.example.porter01.mainUi.home.gridView.PorterVehicleGVAdapter

class HomeFragment : Fragment() {
    private lateinit var binding : FragmentHomeBinding
    private lateinit var vehicleGV: GridView
    private lateinit var homeFragmentDataHelper: HomeFragmentDataHelper
    private lateinit var vehicleList: List<Vehicle>
    private lateinit var bottomSheetDialog : BottomSheetDialog
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeFragmentDataHelper = HomeFragmentDataHelper()   //GVDataHelper
        binding = FragmentHomeBinding.inflate(inflater)
        vehicleGV = binding.gridViewPorters
        vehicleList = homeFragmentDataHelper.getVehicleList()
        bottomSheetDialog = BottomSheetDialog()

        val courseAdapter = PorterVehicleGVAdapter( vehicleList, inflater.context)
        vehicleGV.adapter = courseAdapter
        vehicleGV.onItemClickListener = AdapterView.OnItemClickListener {  parent, view, position,  id ->
            Toast.makeText(inflater.context, vehicleList[position].type + " selected $parent", Toast.LENGTH_SHORT).show()
            bottomSheetDialog.show(parentFragmentManager,bottomSheetDialog.tag)
        }
        return binding.root
    }

}


