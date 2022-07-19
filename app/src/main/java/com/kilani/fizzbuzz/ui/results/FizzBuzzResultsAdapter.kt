package com.kilani.fizzbuzz.ui.results

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kilani.fizzbuzz.R


class FizzBuzzResultsAdapter(private val fizzBuzzList: List<String>) :
    RecyclerView.Adapter<FizzBuzzResultsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView

        init {
            textView = view.findViewById(R.id.fizz_buzz_result_text)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.fizz_buzz_result_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.textView.text = fizzBuzzList[position]
    }

    override fun getItemCount() = fizzBuzzList.size

}