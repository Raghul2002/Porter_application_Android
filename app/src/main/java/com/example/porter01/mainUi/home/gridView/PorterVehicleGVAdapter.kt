package com.example.porter01.mainUi.home.gridView

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.porter01.R

internal class PorterVehicleGVAdapter(
    private val vehicleList: List<Vehicle>,
    private val context: Context
) : BaseAdapter() {
    private var layoutInflater: LayoutInflater? = null
    private lateinit var vehicleTypeTV: TextView
    private lateinit var vehicleDescriptionTV: TextView
    private lateinit var vehicleIV: ImageView

    override fun getCount(): Int {
        return vehicleList.size
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    @SuppressLint("InflateParams")
    override fun getView(position: Int, _convertView: View?, parent: ViewGroup?): View {
        var convertView :View? = _convertView
        if (layoutInflater == null)
            layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        if (convertView == null)
            convertView = layoutInflater!!.inflate(R.layout.card_view_vehicle, null)
        vehicleTypeTV = convertView!!.findViewById(R.id.vehicle_type_tv)
        vehicleDescriptionTV = convertView.findViewById(R.id.vehicle_description_tv)
        vehicleIV = convertView.findViewById(R.id.vehicle_image)
        vehicleIV.setImageResource(vehicleList[position].image)
        vehicleTypeTV.text = vehicleList[position].type
        vehicleDescriptionTV.text = vehicleList[position].description
        return convertView
    }
}
