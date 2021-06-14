package com.app.tours.fragments

import android.app.DatePickerDialog.OnDateSetListener
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.tours.R
import com.app.tours.RecyclerViewAdapter
import com.app.tours.services.dto.TourDto
import com.app.tours.viewmodels.TourViewModel
import kotlinx.android.synthetic.main.fragment_tour_search.*


class TourSearch : Fragment() {

    private val model: TourViewModel by viewModels()
    private var layoutManager: RecyclerView.LayoutManager?=null
    private var adapter: RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>? = null
    private var sharedPreferences: SharedPreferences? = null


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

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)

        model.getTours()
        model.toursLiveData.observe(viewLifecycleOwner, Observer {
            recyclerView.apply {
                // set a LinearLayoutManager to handle Android
                // RecyclerView behavior
                layoutManager = LinearLayoutManager(activity)
                // set the custom adapter to the RecyclerView
                adapter = RecyclerViewAdapter(it)
            }
            etDateI.setOnClickListener {
                showDatePickerDialog(etDateI)
            }
            etDateF.setOnClickListener {
                showDatePickerDialog(etDateF)
            }
            etSearch.setOnClickListener{
               val list: List<TourDto>? = model.getFilterTours(etName.text.toString(), etDateI.text.toString(), etDateF.text.toString())
                recyclerView.apply {
                    // set a LinearLayoutManager to handle Android
                    // RecyclerView behavior
                    layoutManager = LinearLayoutManager(activity)
                    // set the custom adapter to the RecyclerView
                    adapter = list?.let { it1 -> RecyclerViewAdapter(it1) }
                }
            }

        })
    }



}
