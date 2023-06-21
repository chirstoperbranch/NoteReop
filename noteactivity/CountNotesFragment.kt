package com.example.noteactivity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.color.databinding.FragmentCountNotesBinding

private val FragmentCountNotesBinding.root: View?
    get() {}

class CountNotesFragment : Fragment() {
    private lateinit var viewModel: CountNotesViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentCountNotesBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this).get(CountNotesViewModel::class.java)

        viewModel.noteCount.observe(viewLifecycleOwner) { count ->
            binding.noteCountTextView.text = count.toString()
        }

        binding.addButton.setOnClickListener {
            val title = binding.noteTitleEditText.text.toString()
            val content = binding.noteContentEditText.text.toString()
            val note = Note(title = title, content = content)
            viewModel.insertNote(note)
        }

        return binding.root
    }
}
