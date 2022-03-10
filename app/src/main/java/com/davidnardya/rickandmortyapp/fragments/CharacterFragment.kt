package com.davidnardya.rickandmortyapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.davidnardya.rickandmortyapp.R
import com.davidnardya.rickandmortyapp.adapter.CharactersAdapter
import com.davidnardya.rickandmortyapp.databinding.FragmentCharacterBinding
import com.davidnardya.rickandmortyapp.models.CharacterResult
import com.davidnardya.rickandmortyapp.models.Episode
import com.davidnardya.rickandmortyapp.viewmodel.CharacterFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CharacterFragment(private val character: CharacterResult) : Fragment(),
    CharactersAdapter.MoveToEpisode {

    //Properties
    @Inject
    lateinit var viewModel: CharacterFragmentViewModel
    private lateinit var binding: FragmentCharacterBinding
    private val adapter by lazy { CharactersAdapter(this) }

    //Override methods
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterBinding.inflate(inflater, container, false)

        initAdapter()
        initCharacterFields()
        getDataFromApi()

        return binding.root
    }

    override fun moveToEpisode(episode: Episode) {
        val episodeFragment = EpisodeFragment(episode)
        parentFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.enter_from_right,
                R.anim.exit_to_right,
                R.anim.enter_from_right,
                R.anim.exit_to_right
            )
            .replace(R.id.character_fragment, episodeFragment).addToBackStack(null)
            .commit()
    }

    //Private methods
    private fun initCharacterFields() {
        Glide.with(binding.singleCharacterImage)
            .asBitmap()
            .load(character.image)
            .into(binding.singleCharacterImage)

        val name = "Name: ${character.name}"
        val status = "Status: ${character.status}"
        val species = "Species: ${character.species}"
        val gender = "Gender: ${character.gender}"
        val location = "Location: ${character.location.name}"
        val origin = "Origin: ${character.origin.name}"

        binding.singleCharacterName.text = name
        binding.singleCharacterStatus.text = status
        binding.singleCharacterSpecies.text = species
        binding.singleCharacterGender.text = gender
        binding.singleCharacterLocation.text = location
        binding.singleCharacterOrigin.text = origin
    }

    private fun initAdapter() {
        binding.characterEpisodeRecyclerView.adapter = adapter
        binding.characterEpisodeRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun getDataFromApi() {
        viewModel.getEpisodes(character)
        viewModel.episodes.observe(requireActivity()) { response ->
            if (response != null) {
                binding.characterProgressBar.visibility = View.GONE
                adapter.setData(response)
                Log.d("episodesList", "Loaded successfully")
            } else {
                Log.e("episodesList", "Something went wrong")
            }
        }
    }
}