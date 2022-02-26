package com.davidnardya.rickandmortyapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.davidnardya.rickandmortyapp.R
import com.davidnardya.rickandmortyapp.adapter.MainAdapter
import com.davidnardya.rickandmortyapp.databinding.ActivityMainBinding
import com.davidnardya.rickandmortyapp.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: MainViewModel

    //Properties
    private lateinit var binding: ActivityMainBinding
    private val adapter by lazy { MainAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        defineBinding()
        getDataFromApi()
    }

    //Private methods
    private fun getDataFromApi() {
        viewModel.getCharacters()
        viewModel.charactersList.observe(this, Observer { response ->
            if (response != null) {
                binding.mainProgressbar.visibility = View.GONE
                adapter.setData(response)
                Log.d("charactersList", "Loaded successfully")
            } else {
                Log.e("charactersList", "Something went wrong")
            }
        })
    }

    private fun defineBinding() {
        binding.recyclerview.adapter = adapter
        binding.recyclerview.layoutManager = GridLayoutManager(this, 2)
    }
}