package com.todoList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.todoList.databinding.FragmentSplashBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment : Fragment() {
    private val navController: NavController by lazy { findNavController() }
    private val viewModel: SplashViewModel by viewModel()
    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.showProgress()
        viewModel.progressLiveData.observe(viewLifecycleOwner) {
            binding.progressBarHorizontal.progress = it
            if (it==100)
            navController.navigate(SplashFragmentDirections.actionSplashFragmentToFragmentMain())
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}