package com.davidnardya.rickandmortyapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.davidnardya.rickandmortyapp.databinding.SingleEpisodeItemBinding
import com.davidnardya.rickandmortyapp.models.Episode

class CharactersAdapter(listenerFromFragment: MoveToEpisode) : RecyclerView.Adapter<CharactersAdapter.ViewHolder>() {

    //Properties
    var episodesList = emptyList<Episode>()
    val listener = listenerFromFragment

    //ViewHolder
    inner class ViewHolder(private val binding: SingleEpisodeItemBinding, private val listener: MoveToEpisode) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(episode: Episode) {
            binding.episodeItemNameTextView.text = episode.name
            binding.episodeContainer.setOnClickListener{
                listener.moveToEpisode(episode)
            }
        }
    }

    //RV Methods
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            SingleEpisodeItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            listener
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(episodesList[position])
    }

    override fun getItemCount(): Int {
        return episodesList.size
    }

    //Public methods
    fun setData(newList: List<Episode>) {
        episodesList = newList
        notifyDataSetChanged()
    }

    //Interface
    interface MoveToEpisode {
        fun moveToEpisode(episode: Episode)
    }
}