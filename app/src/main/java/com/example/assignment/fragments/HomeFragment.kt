package com.example.assignment.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.assignment.R
import com.example.assignment.adapter.NotesAdapter
import com.example.assignment.database.Notes
import com.example.assignment.database.NotesDatabase
import com.example.assignment.databinding.FragmentHomeBinding
import com.example.assignment.mvvm.NotesFactoryViewModel
import com.example.assignment.mvvm.NotesRepository
import com.example.assignment.mvvm.NotesViewModel

class HomeFragment : Fragment() {


    lateinit var binding: FragmentHomeBinding
    lateinit var viewModel: NotesViewModel
    lateinit var adapter: NotesAdapter
    var dummyList = listOf<Notes>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        val dao = NotesDatabase.getInstance(requireContext()).notesDao
        val repository = NotesRepository(dao)
        val factory = NotesFactoryViewModel(repository)


        viewModel = ViewModelProvider(this, factory)[NotesViewModel::class.java]
        adapter = NotesAdapter(dummyList)

        binding.recyclerView.layoutManager = GridLayoutManager(context,2)
        viewModel.displayAllNotes.observe(viewLifecycleOwner, Observer {

            adapter = NotesAdapter(it)
            binding.recyclerView.adapter = adapter

        })

        binding.fab.setOnClickListener {

            view?.findNavController()?.navigate(R.id.action_homeFragment_to_createNoteFragment)
        }

        return binding.root




    }
}