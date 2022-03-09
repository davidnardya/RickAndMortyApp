package com.davidnardya.rickandmortyapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.davidnardya.rickandmortyapp.R
import com.davidnardya.rickandmortyapp.databinding.SingleCharacterItemBinding
import com.davidnardya.rickandmortyapp.fragments.CharacterFragment
import com.davidnardya.rickandmortyapp.models.CharacterResult

class MainAdapter :
    PagingDataAdapter<CharacterResult, MainAdapter.CharacterViewHolder>(CHARACTER_COMPARATOR) {

    inner class CharacterViewHolder(private val binding: SingleCharacterItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(character: CharacterResult) {
            binding.apply {
                characterNameTextView.text = character.name

                Glide.with(characterImageView)
                    .asBitmap()
                    .load(character.image)
                    .into(characterImageView)

                characterContainer.setOnClickListener { v ->
                    val activity = v!!.context as AppCompatActivity
                    val characterFragment = CharacterFragment(character)
                    activity.supportFragmentManager.beginTransaction()
                        .setCustomAnimations(
                            R.anim.enter_from_right,
                            R.anim.exit_to_right,
                            R.anim.enter_from_right,
                            R.anim.exit_to_right
                        )
                        .replace(R.id.main_activity, characterFragment).addToBackStack(null)
                        .commit()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding =
            SingleCharacterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    companion object {
        private val CHARACTER_COMPARATOR = object : DiffUtil.ItemCallback<CharacterResult>() {
            override fun areItemsTheSame(
                oldItem: CharacterResult,
                newItem: CharacterResult
            ) = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: CharacterResult,
                newItem: CharacterResult
            ) = oldItem == newItem

        }
    }


}