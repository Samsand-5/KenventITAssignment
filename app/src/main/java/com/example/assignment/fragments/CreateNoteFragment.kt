package com.example.assignment.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.assignment.R
import com.example.assignment.database.NotesDatabase
import com.example.assignment.databinding.FragmentCreateNoteBinding
import com.example.assignment.mvvm.NotesFactoryViewModel
import com.example.assignment.mvvm.NotesRepository
import com.example.assignment.mvvm.NotesViewModel

class CreateNoteFragment : Fragment() {

    lateinit var binding:FragmentCreateNoteBinding
    lateinit var viewModel: NotesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_note, container, false)


        val dao = NotesDatabase.getInstance(requireContext()).notesDao
        val repository = NotesRepository(dao)
        val factory = NotesFactoryViewModel(repository)
        viewModel = ViewModelProvider(this, factory)[NotesViewModel::class.java]


        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.saveBtn.setOnClickListener {
            viewModel.addNotes()
            view?.findNavController()?.navigate(R.id.action_createNoteFragment_to_homeFragment)
        }


        return binding.root
    }


}