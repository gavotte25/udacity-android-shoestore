package com.udacity.shoestore.screens.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.MainViewModel
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentDetailBinding
import com.udacity.shoestore.models.Shoe
import timber.log.Timber
import kotlin.Exception

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)

        binding.cancelBtn.setOnClickListener{findNavController()
            .navigate(DetailFragmentDirections.actionDetailFragmentToListingFragment())}

        binding.mainViewModel = viewModel

//        saveBtn.setOnClickListener is replaced by using live data, triggered at layout
        viewModel.addSuccess.observe(viewLifecycleOwner, Observer { success ->
            when (success) {
                true -> {
                    findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToListingFragment2())
                    viewModel.refreshStatus()
                    viewModel.newShoeRecord()
                }
                false -> {
                    Toast.makeText(context, "Cannot add new shoe, please make sure info is filled!", Toast.LENGTH_SHORT).show()
                    viewModel.refreshStatus()
                }
                else -> {}
            }
        })

        binding.cancelBtn.setOnClickListener{
            viewModel.newShoeRecord()
            findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToListingFragment())
        }

        return binding.root
    }
}