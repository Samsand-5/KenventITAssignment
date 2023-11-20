package com.example.assignment.adapter

import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Note
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.assignment.R
import com.example.assignment.database.Notes
import com.example.assignment.databinding.NoteslistItemBinding

class NotesAdapter(var notesList: List<Notes>): RecyclerView.Adapter<MyHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val layoutInflator = LayoutInflater.from(parent.context)
        val binding : NoteslistItemBinding
                = DataBindingUtil.inflate(layoutInflator,
            R.layout.noteslist_item,
            parent,
            false)
        return MyHolder(binding)
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.bindTheView(notesList[position])
    }

}

class MyHolder(val binding : NoteslistItemBinding) : ViewHolder(binding.root) {

    fun bindTheView(notes: Notes){

        binding.noteTitle.text = notes.title
        binding.noteSubtitle.text = notes.subtitle

        binding.listItemLayout.setOnClickListener{
            // clickListener(notes)

            val bundle  = Bundle()
            bundle.putString("title", notes.title)
            bundle.putString("text", notes.notesText)

            bundle.putString("sub", notes.subtitle)
            bundle.putInt("id", notes.id)
            bundle.putString("id", notes.id.toString())

            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_editNoteFragment,bundle)




        }
    }

}


