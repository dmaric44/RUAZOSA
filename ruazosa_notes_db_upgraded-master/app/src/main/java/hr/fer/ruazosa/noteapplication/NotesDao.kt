package hr.fer.ruazosa.noteapplication


import androidx.room.*

@Dao
interface NotesDao {
    @Query("SELECT * FROM note")
    fun getAll(): List<Note>
    @Insert
    fun insertAll(vararg notes: Note)
    @Delete
    fun delete(note: Note)
    @Query(" UPDATE note SET note_description = :description, note_title = :text WHERE noteId = :id ")
    fun updateNote(description: String, text: String, id: Int)
}