package com.davidnardya.rickandmortyapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.davidnardya.rickandmortyapp.R
import com.davidnardya.rickandmortyapp.databinding.SingleCharacterItemBinding
import com.davidnardya.rickandmortyapp.fragments.CharacterFragment
import com.davidnardya.rickandmortyapp.models.CharacterResult

class MainAdapter : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    //Properties
    var charactersList = emptyList<CharacterResult>()

    //ViewHolder
    inner class ViewHolder(private val binding: SingleCharacterItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(character: CharacterResult) {
            binding.characterNameTextView.text = character.name

            Glide.with(binding.characterImageView)
                .asBitmap()
                .load(character.image)
                .into(binding.characterImageView)

            binding.characterContainer.setOnClickListener { v ->
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

    //RV Methods
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            SingleCharacterItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(charactersList[position])
    }

    override fun getItemCount(): Int {
        return charactersList.size
    }

    //Public methods
    fun setData(newList: List<CharacterResult>) {
        charactersList = newList
        notifyDataSetChanged()
    }
}