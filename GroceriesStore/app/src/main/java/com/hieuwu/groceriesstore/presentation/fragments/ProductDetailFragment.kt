package com.hieuwu.groceriesstore.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.hieuwu.groceriesstore.R
import com.hieuwu.groceriesstore.databinding.FragmentProductDetailBinding
import com.hieuwu.groceriesstore.di.ProductRepo
import com.hieuwu.groceriesstore.domain.repository.ProductRepository
import com.hieuwu.groceriesstore.presentation.viewmodels.ProductDetailViewModel
import com.hieuwu.groceriesstore.presentation.viewmodels.factory.ProductDetailViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProductDetailFragment : Fragment() {
    private lateinit var binding: FragmentProductDetailBinding


    @ProductRepo
    @Inject
    lateinit var productRepository: ProductRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val args = ProductDetailFragmentArgs.fromBundle(arguments as Bundle)

        binding = DataBindingUtil.inflate<FragmentProductDetailBinding>(
            inflater, R.layout.fragment_product_detail, container, false
        )

        val viewModelFactory = ProductDetailViewModelFactory( args.id,productRepository)
        val viewModel = ViewModelProvider(this, viewModelFactory)
            .get(ProductDetailViewModel::class.java)
        binding.viewModel = viewModel

        binding.lifecycleOwner = this
        return binding.root
    }

}