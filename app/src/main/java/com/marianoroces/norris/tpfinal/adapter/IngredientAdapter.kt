package com.marianoroces.norris.tpfinal.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.marianoroces.norris.tpfinal.R

class IngredientAdapter(private val ingredientes: ArrayList<String>) :
    RecyclerView.Adapter<IngredientAdapter.ViewHolder>() {


        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

            val ingrediente: TextView

            init {
                ingrediente = view.findViewById(R.id.list_item_title)
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.ingrediente.text = ingredientes[position]
    }

    override fun getItemCount(): Int {
        return ingredientes.size
    }
}