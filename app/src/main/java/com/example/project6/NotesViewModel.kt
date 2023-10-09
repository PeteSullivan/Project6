package com.example.project6

import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class NotesViewModel(val dao: NoteDao) : ViewModel() {
    var newNoteName = ""
    var newNoteDescription = ""
    private val notes = dao.getAll()
    val notesString = notes.map {
            it -> formatNotes(it)
    }
    fun addNote() {
        viewModelScope.launch {
            val note = Note()
            note.noteTitle = newNoteName
            note.noteDescription = newNoteDescription
            dao.insert(note)
        }
    }
    fun formatNotes(tasks: List<Note>): String {
        return tasks.fold("") {
                str, item -> str + '\n' + formatNote(item)
        }
    }
    fun formatNote(task: Note): String {
        var str = "ID: ${task.noteId}"
        str += '\n' + "Title: ${task.noteTitle}"
        str += '\n' + "Description: ${task.noteDescription}" + '\n'
        return str
    }
}