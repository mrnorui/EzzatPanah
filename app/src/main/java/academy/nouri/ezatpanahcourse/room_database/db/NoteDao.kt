package academy.nouri.ezatpanahcourse.room_database.db

import academy.nouri.ezatpanahcourse.utils.NOTE_TABLE
import android.icu.text.CaseMap
import androidx.room.*

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(note: NoteModel)

    @Update
    fun updateNote(note: NoteModel)

    @Delete
    fun deleteNote(note: NoteModel)

    @Query("SELECT * FROM $NOTE_TABLE ORDER BY note_id DESC")
    fun getAllNotes(): MutableList<NoteModel>

    @Query("SELECT * FROM $NOTE_TABLE WHERE note_id LIKE :id")
    fun getNote(id: Int): NoteModel

    @Query("DELETE FROM $NOTE_TABLE")
    fun deleteAllNotes()

    @Query("SELECT * FROM $NOTE_TABLE WHERE note_title LIKE :title")
    fun searchNote(title: String): MutableList<NoteModel>
}