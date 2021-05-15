package com.udacity.shoestore.screens.listing

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.MainViewModel
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentListingBinding

class ListingFragment : Fragment() {

    private lateinit var binding: FragmentListingBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_listing, container, false)

        viewModel.shoesList.observe(viewLifecycleOwner, {
            for (shoe in it) {
                val imageView = ImageView(context)
                val imageRes = this.resources.getIdentifier(
                    shoe.images.first(),
                    "drawable",
                    requireActivity().packageName)
                imageView.setImageResource(imageRes)
                imageView.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    resources.getDimension(R.dimen.image_height).toInt())
                binding.imageListLayout.addView(imageView)
            }
        })

        binding.addFloatingBtn.setOnClickListener { findNavController()
            .navigate(ListingFragmentDirections.actionListingFragmentToDetailFragment()) }

        return binding.root
    }
}