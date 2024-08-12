package com.test.chessmoves

import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.test.chessmoves.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var chessAdapter: MovesAdapter
    private val cells = mutableListOf<Cell>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding  = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.generateMatrixButton.setOnClickListener {
            val sizeInput = binding.matrixSizeInput.text.toString()
            if (!TextUtils.isEmpty(sizeInput)) {
                val size = sizeInput.toInt()
                if (size > 0) {
                    setupBoard(size)
                } else {
                    Toast.makeText(this, "Please enter a valid size", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please enter the matrix size", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupBoard(size: Int) {
        cells.clear()
        for (row in 0 until size) {
            for (col in 0 until size) {
                cells.add(Cell(row, col))
            }
        }
        chessAdapter = MovesAdapter(cells)
        binding.recyclerView.layoutManager = GridLayoutManager(this, size)
        binding.recyclerView.adapter = chessAdapter
    }
}