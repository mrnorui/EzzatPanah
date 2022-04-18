package academy.nouri.ezatpanahcourse

import academy.nouri.ezatpanahcourse.databinding.ItemHousesBinding
import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HousesAdapter constructor(private val items: MutableList<HousesModel>) : RecyclerView.Adapter<HousesAdapter.ViewHolder>() {

    private lateinit var binding: ItemHousesBinding
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        context = parent.context
        binding = ItemHousesBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    inner class ViewHolder(itemView: ItemHousesBinding) : RecyclerView.ViewHolder(itemView.root) {

        @SuppressLint("SetTextI18n")
        fun bind(item: HousesModel) {
            binding.apply {
                itemHousesImg.setImageResource(item.img)
                itemHousesPrice.text = "${item.price} تومان"
                itemHousesAddress.text = item.address
                //Click
                itemHousesImg.setOnClickListener {

                }
            }
        }
    }
}