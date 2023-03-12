package com.todoList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.todoList.databinding.FragmentMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentMain : Fragment() {
    private val viewModel: MainViewModel by viewModel()
    private val navController: NavController by lazy { findNavController() }
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getAll()

        viewModel.list.observe(viewLifecycleOwner) {
            binding.mainRv.adapter = ListAdapter(
                it,
                clickDelete = { viewModel.deleteTodo(it) },
                onClick = {
                    navController.navigate(
                        FragmentMainDirections.actionFragmentMainToTextEditorFragment(it))
                })
        }

        binding.floatingActionButton.setOnClickListener {
            navController.navigate(
                FragmentMainDirections.actionFragmentMainToTextEditorFragment(null))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}