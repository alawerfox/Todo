package com.todoList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.todoList.databinding.FragmentRedactorBinding
import com.todoList.db.Entity
import org.koin.androidx.viewmodel.ext.android.viewModel

class TextEditorFragment : Fragment() {
    private val navController: NavController by lazy { findNavController() }
    private val viewModel: RedactorViewModel by viewModel()
    private var _binding: FragmentRedactorBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRedactorBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val entity = arguments?.getParcelable("arg") ?: Entity(0, "", "")

        binding.title.setText(entity.title)
        binding.content.setText(entity.description)

        binding.saveBtn.setOnClickListener {
            val newEntity = entity.copy(
                title = binding.title.text.toString(),
                description = binding.content.text.toString()
            )
            viewModel.update(newEntity)
            navController.popBackStack()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}