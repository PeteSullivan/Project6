package com.example.project6

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.project6.databinding.FragmentNoteBinding



class NotesFragment : Fragment() {
    private var _binding: FragmentNoteBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNoteBinding.inflate(inflater, container, false)
        val view = binding.root
        val application = requireNotNull(this.activity).application
        val dao = NoteDatabase.getInstance(application).noteDao

        val noteId = arguments?.getInt("noteId")?.toLong() ?: 1L //temporary solution to having no arguments.

        /*
        atm, I hardcoded in that I only edit the 2nd note. b/c of an argument bug I had, I couldn't find a way
        to edit different notes. However, this does show the database is setup and working, and the notes can be
        added and updated to the database.
         */

        val viewModelFactory = NotesViewModelFactory(2L, dao)
        val viewModel = ViewModelProvider(
            this, viewModelFactory).get(NotesViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.navigateToList.observe(viewLifecycleOwner, Observer { navigate ->
            if (navigate) {
                view.findNavController()
                    .navigate(R.id.note_to_menu)
                viewModel.onNavigatedToList()
            }
        })
        /*
        supposed to update the current note, but it crashes the app bc I can't pass the noteId to
        this fragment.
         */
        binding.saveButton.setOnClickListener {
            viewModel.updateNote()
            view.findNavController()
                .navigate(R.id.note_to_menu)
        }

        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}