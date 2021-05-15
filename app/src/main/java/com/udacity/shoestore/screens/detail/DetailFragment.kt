package com.udacity.shoestore.screens.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.MainViewModel
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentDetailBinding
import com.udacity.shoestore.models.Shoe
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

        binding.saveBtn.setOnClickListener{
            try {
                val shoe = Shoe(
                    binding.shoeNameEditText.text.toString(),
                    binding.shoeSizeEditText.text.toString().toDouble(),
                    binding.companyEditText.text.toString(),
                    binding.descriptionEditText.text.toString(),
                    mutableListOf("blank")
                )
                if (viewModel.addShoe(shoe)) {
                    findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToListingFragment())
                }
                else {
                    Toast.makeText(context, "Cannot add new shoe, please try again!", Toast.LENGTH_LONG).show()
                }
            }
            catch (e: Exception) {
                Toast.makeText(context, "Invalid input, please recheck the information", Toast.LENGTH_LONG).show()
            }
        }

        binding.cancelBtn.setOnClickListener{findNavController()
            .navigate(DetailFragmentDirections.actionDetailFragmentToListingFragment())}

        return binding.root
    }
}