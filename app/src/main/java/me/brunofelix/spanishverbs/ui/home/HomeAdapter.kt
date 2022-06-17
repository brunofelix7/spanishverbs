package me.brunofelix.spanishverbs.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import me.brunofelix.spanishverbs.data.Verb
import me.brunofelix.spanishverbs.databinding.ItemVerbBinding

class HomeAdapter : PagingDataAdapter<Verb, HomeAdapter.MainViewHolder>(DIFF_CALLBACK) {

    var listener: HomeClickListener? = null
    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val root = ItemVerbBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(root, listener, context)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Verb>() {
            override fun areItemsTheSame(oldItem: Verb, newItem: Verb) = oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Verb, newItem: Verb) = oldItem == newItem
        }
    }

    /**
     * My ViewHolder
     */
    inner class MainViewHolder constructor(
        private val binding: ItemVerbBinding,
        private val listener: HomeClickListener?,
        private val context: Context
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(verb: Verb) {
            binding.tvVerb.text = verb.name
            binding.tvGerund.text = verb.gerund

            binding.content.setOnClickListener {
                listener?.onItemClick(verb)
            }

            binding.ivFavorite.setOnClickListener {
                listener?.onFavoriteClick(verb)
            }
        }
    }
}