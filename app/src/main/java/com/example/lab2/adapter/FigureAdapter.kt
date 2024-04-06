package com.example.lab2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.lab2.databinding.ItemFigureBinding
import com.example.lab2.model.Figure
import com.example.lab22.adapter.FigureDiffUtil

class FigureAdapter(private var figures: List<Figure>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class FigureViewHolder(private val binding: ItemFigureBinding) : RecyclerView.ViewHolder(binding.root) {
        val figureName: TextView = binding.figureName
        val figureTitle: TextView = binding.figureTitle
        val figureBorn: TextView = binding.figureBorn
        val figureDied: TextView = binding.figureDied
        val figureNationality: TextView = binding.figureNationality

        fun bind(figure: Figure) {
            figureName.text = "${figure.name}"
            figureTitle.text = "Known as: ${figure.title}"

            figure.info?.let { info ->
                figureBorn.text = "Born: ${info.born}"
                figureDied.text = "Died: ${info.died}"
                figureNationality.text = "Nationality: ${info.nationality}"
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemFigureBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FigureViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val figure = figures[position]
        if (holder is FigureViewHolder) {
            holder.bind(figure)
        }
    }

    override fun getItemCount() = figures.size

    fun submitList(newFigures: List<Figure>) {
        val diffResult = DiffUtil.calculateDiff(FigureDiffUtil(figures, newFigures))
        figures = newFigures
        diffResult.dispatchUpdatesTo(this)
    }
}
