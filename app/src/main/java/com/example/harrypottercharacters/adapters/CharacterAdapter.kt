package com.example.harrypottercharacters.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.harrypottercharacters.R
import com.example.harrypottercharacters.models.Character

class CharacterAdapter(var dataset: List<Character>) : RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    private var data : List<Character> = dataset

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)

        val view = inflater.inflate(R.layout.view_character, parent, false)
        val vh = ViewHolder(view)

        return vh
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = data.get(position)

        holder.name.text = character.name
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setData(dataset: List<Character>) {
        data = dataset.sortedBy { character -> character.name }
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.char_recycler_name)
    }
}