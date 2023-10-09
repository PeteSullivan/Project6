package com.example.project6

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
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
        val viewModelFactory = NotesViewModelFactory(dao)
        val viewModel = ViewModelProvider(
            this, viewModelFactory).get(NotesViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

//        val adapter = NoteItemAdapter{ noteId ->
//            viewModel.onTaskClicked(noteId)
//        }
//        binding.tasksList.adapter = adapter
//
//        viewModel.tasks.observe(viewLifecycleOwner, Observer {
//            it?.let {
//                adapter.submitList(it)
//            }
//        })
//
//        viewModel.navigateToTask.observe(viewLifecycleOwner, Observer { taskId ->
//            taskId?.let {
//                val action = TasksFragmentDirections
//                    .actionTasksFragmentToEditTaskFragment(taskId)
//                this.findNavController().navigate(action)
//                viewModel.onTaskNavigated()
//            }
//        })



        binding.saveButton.setOnClickListener {
            view.findNavController().navigate(R.id.note_to_menu)
            viewModel.addNote()
        }

        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}