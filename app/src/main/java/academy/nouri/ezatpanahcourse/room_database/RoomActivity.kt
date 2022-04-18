package academy.nouri.ezatpanahcourse.room_database

import academy.nouri.ezatpanahcourse.MyApp
import academy.nouri.ezatpanahcourse.databinding.ActivityRoomBinding
import academy.nouri.ezatpanahcourse.room_database.db.NoteDatabase
import academy.nouri.ezatpanahcourse.room_database.db.NoteModel
import academy.nouri.ezatpanahcourse.utils.NOTE_DATABASE
import academy.nouri.ezatpanahcourse.utils.showHide
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.room.Room

class RoomActivity : AppCompatActivity() {
    //Binding
    private lateinit var binding: ActivityRoomBinding

    //Database
    private val noteDb: NoteDatabase by lazy {
        Room.databaseBuilder(this, NoteDatabase::class.java, NOTE_DATABASE)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    //Adapter
    private val noteAdapter by lazy { NotesAdapter(notesList) }
    private val notesList: MutableList<NoteModel> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //InitViews
        binding.apply {
            //Add page
            addNoteBtn.setOnClickListener {
                startActivity(Intent(this@RoomActivity, AddNoteActivity::class.java))
            }
            //Show notes
            checkItems()
        }
    }

    private fun checkItems() {
        if (noteDb.noteDao().getAllNotes().isNotEmpty()) {
            binding.emptyText.showHide(false)
            binding.notesList.showHide(true)
            //Setup recyclerview
            setupRecycler()

        } else {
            binding.emptyText.showHide(true)
            binding.notesList.showHide(false)
        }
    }

    private fun setupRecycler() {
        notesList.clear()
        notesList.addAll(noteDb.noteDao().getAllNotes())

        binding.notesList.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = noteAdapter
        }
    }

}