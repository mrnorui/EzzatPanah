package academy.nouri.ezatpanahcourse.room_database

import academy.nouri.ezatpanahcourse.R
import academy.nouri.ezatpanahcourse.databinding.ActivityAddNoteBinding
import academy.nouri.ezatpanahcourse.databinding.ActivityRoomBinding
import academy.nouri.ezatpanahcourse.room_database.db.NoteDatabase
import academy.nouri.ezatpanahcourse.room_database.db.NoteModel
import academy.nouri.ezatpanahcourse.utils.NOTE_DATABASE
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.google.android.material.snackbar.Snackbar

class AddNoteActivity : AppCompatActivity() {
    //Binding
    private lateinit var binding: ActivityAddNoteBinding

    //Database
    private val noteDb: NoteDatabase by lazy {
        Room.databaseBuilder(this, NoteDatabase::class.java, NOTE_DATABASE)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    //Other
    private lateinit var noteModel: NoteModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //InitViews
        binding.apply {
            //Save
            saveBtn.setOnClickListener {
                val title = titleEdt.text.toString()
                val info = infoEdt.text.toString()

                if (title.isNotEmpty() && info.isNotEmpty()) {
                    noteModel = NoteModel(0, title, info)
                    noteDb.noteDao().insertNote(noteModel)
                    finish()
                } else {
                    Snackbar.make(it, "Title and info cannot be empty", Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }
}