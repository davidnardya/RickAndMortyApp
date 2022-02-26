package com.davidnardya.rickandmortyapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.davidnardya.rickandmortyapp.adapter.EpisodesCharactersAdapter
import com.davidnardya.rickandmortyapp.databinding.FragmentEpisodeBinding
import com.davidnardya.rickandmortyapp.models.Episode
import com.davidnardya.rickandmortyapp.viewmodel.EpisodeFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class EpisodeFragment(private val episode: Episode) : Fragment() {

    @Inject
    lateinit var viewModel: EpisodeFragmentViewModel

    private lateinit var binding: FragmentEpisodeBinding
    private val adapter by lazy { EpisodesCharactersAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEpisodeBinding.inflate(inflater, container, false)

        initAdapter()
        initEpisodeFields()
        getDataFromApi()

        return binding.root
    }

    //Private methods
    private fun initAdapter() {
        binding.episodeCharactersRecyclerView.adapter = adapter
        binding.episodeCharactersRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun initEpisodeFields() {
        val name = "Name: ${episode.name}"
        val airDate = "Air Date: ${episode.airDate}"
        val episodeNumber = "Episode Number: ${episode.episode}"

        binding.episodeNameTextView.text = name
        binding.episodeAirDateTextView.text = airDate
        binding.episodeNumberTextView.text = episodeNumber
    }

    private fun getDataFromApi() {
        viewModel.getCharacters(episode)
        viewModel.charactersList.observe(requireActivity()) { response ->
            if (response != null) {
                binding.episodeProgressbar.visibility = View.GONE
                adapter.setData(response)
                Log.d("episodeCharactersList", "Loaded successfully")
            } else {
                Log.e("episodeCharactersList", "Something went wrong")
            }
        }
    }
}