package com.test.chessmoves

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MovesAdapter(private val cells: List<Cell>) : RecyclerView.Adapter<MovesAdapter.ChessViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChessViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cell, parent, false)
        return ChessViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChessViewHolder, position: Int) {
        holder.bind(cells[position])
    }

    override fun getItemCount() = cells.size

    inner class ChessViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(cell: Cell) {
            itemView.apply {
                if (cell.isMoves) {
                    setBackgroundResource(R.color.green)
                } else if (cell.isSelected) {
                    setBackgroundResource(R.color.black)
                } else {
                    setBackgroundResource(R.color.white)
                }

                setOnClickListener {
                    highlightMoves(cell)
                    notifyDataSetChanged()
                }
            }
        }

        private fun highlightMoves(cell: Cell) {
            cells.forEach { it.isSelected = false }

            cells.forEach {
                if (it.row == cell.row || it.col == cell.col || 
                    Math.abs(it.row - cell.row) == Math.abs(it.col - cell.col)) {
                    it.isSelected = true
                }
            }

            cell.isMoves = true
        }
    }
}