package com.udacity.shoestore.screens.listing

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
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
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.logout_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(findNavController())||super.onOptionsItemSelected(item)
    }
}