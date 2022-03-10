package com.davidnardya.rickandmortyapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.paging.map
import androidx.recyclerview.widget.GridLayoutManager
import com.davidnardya.rickandmortyapp.R
import com.davidnardya.rickandmortyapp.adapter.MainAdapter
import com.davidnardya.rickandmortyapp.databinding.ActivityMainBinding
import com.davidnardya.rickandmortyapp.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    //Properties
    @Inject
    lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private val mainAdapterPaging by lazy { MainAdapter() }

    //Override methods
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setRecyclerViewBinding()
        getData()
    }

    //Private methods
    private fun getData() {
        viewModel.characters.observe(this) {
            if (it != null) {
                binding.mainProgressbar.visibility = View.GONE
                mainAdapterPaging.submitData(this.lifecycle, it)
                it.map { character ->
                    viewModel.saveCharacterToDB(character)
                }
            }
        }
    }

    private fun setRecyclerViewBinding() {
        binding.apply {
            recyclerview.setHasFixedSize(true)
            recyclerview.layoutManager = GridLayoutManager(this@MainActivity, 2)
            recyclerview.adapter = mainAdapterPaging
        }
    }

}