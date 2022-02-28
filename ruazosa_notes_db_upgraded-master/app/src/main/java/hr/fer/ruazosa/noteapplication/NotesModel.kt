package hr.fer.ruazosa.noteapplication

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.Date

@Entity(tableName = "note")
 class Note : Serializable {
    @PrimaryKey(autoGenerate = true)
    var noteId: Int? = null
    @ColumnInfo(name = "note_title")
    var noteTitle: String? = null
    @ColumnInfo(name = "note_description")
    var noteDescription: String? = null
    @ColumnInfo(name = "note_date")
    var noteDate: Date? = null
}