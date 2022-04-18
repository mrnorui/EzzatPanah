package academy.nouri.ezatpanahcourse.room_database

import academy.nouri.ezatpanahcourse.R
import academy.nouri.ezatpanahcourse.databinding.ActivityAddNoteBinding
import academy.nouri.ezatpanahcourse.databinding.ActivityUpdateNoteBinding
import academy.nouri.ezatpanahcourse.room_database.db.NoteDatabase
import academy.nouri.ezatpanahcourse.room_database.db.NoteModel
import academy.nouri.ezatpanahcourse.utils.BUNDLE_NOTE_ID
import academy.nouri.ezatpanahcourse.utils.NOTE_DATABASE
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room

class UpdateNoteActivity : AppCompatActivity() {
    //Binding
    private lateinit var binding: ActivityUpdateNoteBinding

    //Database
    private val noteDb: NoteDatabase by lazy {
        Room.databaseBuilder(this, NoteDatabase::class.java, NOTE_DATABASE)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    //Other
    private lateinit var noteModel: NoteModel
    private var noteID = 0
    private var defaultTitle = ""
    private var defaultInfo = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Intent
        intent.extras?.let { itBundle ->
            noteID = itBundle.getInt(BUNDLE_NOTE_ID)
        }
        //InitViews
        binding.apply {
            //Set data
            defaultTitle = noteDb.noteDao().getNote(noteID).noteTitle
            defaultInfo = noteDb.noteDao().getNote(noteID).noteInfo
            titleEdt.setText(defaultTitle)
            infoEdt.setText(defaultInfo)
            //Update
            updateBtn.setOnClickListener {
                val title = titleEdt.text.toString()
                val desc = infoEdt.text.toString()
                //Validation
                if (title.isNotEmpty() && desc.isNotEmpty()) {
                    noteModel = NoteModel(noteID, title, desc)
                    noteDb.noteDao().updateNote(noteModel)
                    finish()
                }
            }
            //Delete
            deleteBtn.setOnClickListener {
                noteModel = NoteModel(noteID, defaultTitle, defaultInfo)
                noteDb.noteDao().deleteNote(noteModel)
                finish()
            }
        }
    }
}