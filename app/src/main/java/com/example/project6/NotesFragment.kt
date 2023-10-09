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
        val noteId = 0L

        val viewModelFactory = NotesViewModelFactory(noteId, dao)
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

        binding.saveButton.setOnClickListener {

            view.findNavController().navigate(R.id.note_to_menu)

        }

        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}