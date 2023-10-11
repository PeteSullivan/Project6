package com.example.project6

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.project6.databinding.FragmentMenuBinding


class MenuFragment : Fragment() {
    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        val view = binding.root
        val application = requireNotNull(this.activity).application
        val dao = NoteDatabase.getInstance(application).noteDao
        val viewModelFactory = MenuViewModelFactory(dao)
        val viewModel = ViewModelProvider(
            this, viewModelFactory).get(MenuViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner


        val adapter = NoteItemAdapter{ noteId ->
            viewModel.onNoteClicked(noteId)
        }
        binding.notesList.adapter = adapter

        viewModel.notes.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        /*
        ran into a bug where I couldn't use arguments to pass data between fragments. Tried to use
        bundles to pass the noteId to NotesFragment, but it doesn't work atm. During testing, I could
        add notes, update notes, and delete notes, but I could never locate which note to change bc
        of the argument bug.
         */
        viewModel.navigateToNote.observe(viewLifecycleOwner, Observer { noteId ->
            noteId?.let {
                val id = noteId ?: 0
                val bundle = bundleOf("noteId" to id)
                view.findNavController().navigate(R.id.menu_to_note, bundle)
                viewModel.onNoteNavigated()
            }
        })
        binding.addNoteButton.setOnClickListener {
            viewModel.addNote()

            val id = viewModel.notes.value
            val bundle = bundleOf("noteId" to id)
            view.findNavController().navigate(R.id.menu_to_note, bundle)

            viewModel.onNoteNavigated()
        }
        return view

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}