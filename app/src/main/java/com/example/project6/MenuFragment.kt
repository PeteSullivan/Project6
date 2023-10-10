package com.example.project6

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
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

        viewModel.navigateToNote.observe(viewLifecycleOwner, Observer { noteId ->
            noteId?.let {
                val action = MenuFragmentDirections
                    .menuToNote()
                this.findNavController().navigate(action)
                binding.curID = noteId
                viewModel.onNoteNavigated()
            }
        })
        binding.addNoteButton.setOnClickListener {
            view.findNavController().navigate(R.id.menu_to_note)
        }
        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}