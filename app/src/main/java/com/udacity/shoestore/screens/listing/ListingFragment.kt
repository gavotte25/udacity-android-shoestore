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
import com.udacity.shoestore.databinding.ShoeLayoutBinding
import com.udacity.shoestore.models.Shoe

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
                addShoeView(shoe, binding.imageListLayout)
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

    private fun addShoeView(shoe: Shoe, container: ViewGroup) {
        var shoeViewBinding: ShoeLayoutBinding = DataBindingUtil
            .inflate(LayoutInflater.from(context), R.layout.shoe_layout, container,false)
        shoeViewBinding.shoe = shoe
        val imageRes = this.resources.getIdentifier(
            shoe.images.first(),
            "drawable",
            requireActivity().packageName)
        shoeViewBinding.tplShoeImg.setImageResource(imageRes)
        container.addView(shoeViewBinding.root)
    }
}