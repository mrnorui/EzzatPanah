package academy.nouri.mvp.ui.main

import academy.nouri.mvp.data.model.home.ResponseMoviesList
import academy.nouri.mvp.databinding.ItemMoviesBinding
import academy.nouri.mvp.ui.detail.DetailActivity
import academy.nouri.mvp.utils.Constants
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class MoviesAdapter constructor(private val items: MutableList<ResponseMoviesList.Data>) :
    RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    private lateinit var binding: ItemMoviesBinding
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        context = parent.context
        binding = ItemMoviesBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int): Int {
        return position
    }

    inner class ViewHolder(itemView: ItemMoviesBinding) : RecyclerView.ViewHolder(itemView.root) {

        @SuppressLint("SetTextI18n")
        fun bind(item: ResponseMoviesList.Data) {
            binding.apply {
                binding.apply {
                    itemMoviesName.text = item.title
                    itemMoviesRate.text = item.imdbRating.toString()
                    //Coil
/*                    itemMoviesImg.load(item.poster){
                        crossfade(true)
                        crossfade(800)
                    }*/

                    //Glide
                    Glide.with(context)
                        .load(item.poster)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                        .into(itemMoviesImg)
                    //Click
                    root.setOnClickListener {
                        Intent(context, DetailActivity::class.java).also {
                            it.putExtra(Constants.MOVIE_ID, item.id)
                            context.startActivity(it)
                        }
                    }
                }
            }
        }
    }
}