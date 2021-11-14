package com.example.recyclerview_update_2.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.recyclerview_update_2.R
import com.example.recyclerview_update_2.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: MainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout XML file and return a binding object instance
        binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val callbackClickItem = { pos: Int ->
            viewModel.remove(pos)
        }
        val itemAdapter=ItemAdapter(callbackClickItem)
        binding.recyclerView.adapter = itemAdapter

        binding.fab.setOnClickListener{
            viewModel.addItem()
        }

        viewModel.items.observe(viewLifecycleOwner, {
            it?.let {
                itemAdapter.submitList(it)
                binding.nbItems.text = activity?.resources?.getString(R.string.nbItems, viewModel.items.value?.size.toString())
            }
        })
    }
}