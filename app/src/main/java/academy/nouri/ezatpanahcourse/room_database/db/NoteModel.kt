package academy.nouri.ezatpanahcourse.room_database.db

import academy.nouri.ezatpanahcourse.utils.NOTE_TABLE
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = NOTE_TABLE)
data class NoteModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "note_id")
    val noteId: Int,
    @ColumnInfo(name = "note_title")
    val noteTitle: String,
    val noteInfo: String
)