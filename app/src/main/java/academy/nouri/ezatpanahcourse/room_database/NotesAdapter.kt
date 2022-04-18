package academy.nouri.ezatpanahcourse.room_database

import academy.nouri.ezatpanahcourse.R
import academy.nouri.ezatpanahcourse.databinding.ItemNotesBinding
import academy.nouri.ezatpanahcourse.room_database.db.NoteModel
import academy.nouri.ezatpanahcourse.utils.BUNDLE_NOTE_ID
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class NotesAdapter constructor(private val items: MutableList<NoteModel>) : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    private lateinit var binding: ItemNotesBinding
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        context = parent.context
        binding = ItemNotesBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    inner class ViewHolder(itemView: ItemNotesBinding) : RecyclerView.ViewHolder(itemView.root) {

        @SuppressLint("SetTextI18n")
        fun bind(item: NoteModel) {
            binding.apply {
                itemTitle.text = item.noteTitle
                itemDesc.text = item.noteInfo
                //Set color
                val random = Random()
                val color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256))
                itemColor.setBackgroundColor(color)
                //Click
                root.setOnClickListener {
                    val intent = Intent(context, UpdateNoteActivity::class.java)
                    intent.putExtra(BUNDLE_NOTE_ID, item.noteId)
                    context.startActivity(intent)
                }
            }
        }
    }
}