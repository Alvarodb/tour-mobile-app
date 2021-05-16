package com.app.tours.fragments

import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.tours.R
import com.app.tours.RecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_tour_search.*


class TourSearch : Fragment() {

     var layoutManager: RecyclerView.LayoutManager?=null
     var adapter: RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>? = null


    private fun twoDigits(n: Int): String? {
        return if (n <= 9) "0$n" else n.toString()
    }

    private fun showDatePickerDialog(editText: EditText) {
        val newFragment =
            DatePickerFragment.newInstance(OnDateSetListener { datePicker, year, month, day ->
                val selectedDate: String =
                    twoDigits(day).toString() + "/" + twoDigits(month + 1) + "/" + year
                editText.setText(selectedDate)
            })
        newFragment.show(requireActivity().supportFragmentManager, "datePicker")
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tour_search, container, false)



    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        recyclerView.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            layoutManager = LinearLayoutManager(activity)
            // set the custom adapter to the RecyclerView
            adapter = RecyclerViewAdapter()
        }
        etDateI.setOnClickListener {
            showDatePickerDialog(etDateI)
        }
        etDateF.setOnClickListener {
            showDatePickerDialog(etDateF)
        }
    }


}
