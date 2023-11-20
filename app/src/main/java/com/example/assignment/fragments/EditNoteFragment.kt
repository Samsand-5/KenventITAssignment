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
import com.example.assignment.databinding.FragmentEditNoteBinding
import com.example.assignment.mvvm.NotesFactoryViewModel
import com.example.assignment.mvvm.NotesRepository
import com.example.assignment.mvvm.NotesViewModel

class EditNoteFragment : Fragment() {

    lateinit var viewModel: NotesViewModel
    lateinit var binding: FragmentEditNoteBinding

    lateinit var title : String
    lateinit var note: String
    lateinit var sub: String
    lateinit var id: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_edit_note, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner

        val dao = NotesDatabase.getInstance(requireContext()).notesDao
        val repository = NotesRepository(dao)
        val factory = NotesFactoryViewModel(repository)
        viewModel = ViewModelProvider(this, factory)[NotesViewModel::class.java]
        binding.viewModel = viewModel



        title = requireArguments().getString("title").toString()
        sub = requireArguments().getString("sub").toString()
        note = requireArguments().getString("text").toString()
        id = requireArguments().getString("id").toString()

        binding.titleEt.setText(title)
        binding.noteEt.setText(note)
        binding.subet.setText(sub)

        binding.updateNoteBtn.setOnClickListener {

            val newid = id.toInt()
            val newtitle = binding.titleEt.getText()
            val newsub = binding.subet.getText()
            val newnote = binding.noteEt.getText()

            viewModel.editUpdate(newid, newtitle.toString(), newsub.toString(), newnote.toString())
            view.findNavController().navigate(R.id.action_editNoteFragment_to_homeFragment)


        }
    }

}