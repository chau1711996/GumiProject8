package com.example.gumiproject8.ui.favorite

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gumiproject8.R
import com.example.gumiproject8.databinding.FragmentDetailBinding
import com.example.gumiproject8.databinding.FragmentDetailFavoriteBinding
import com.example.gumiproject8.model.favorite.FavoriteEntity
import com.example.gumiproject8.model.mymodel.ProductModel
import com.example.gumiproject8.ui.MyAdapter
import com.example.gumiproject8.ui.MyDetailAdapter
import com.example.gumiproject8.ui.MyViewModel
import com.example.gumiproject8.ui.favorite.comic.FavoriteComicFragment
import com.example.gumiproject8.utils.gone
import com.example.gumiproject8.utils.visible
import org.koin.android.viewmodel.ext.android.viewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailFavoriteFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var category: FavoriteEntity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            category = it.getSerializable(ARG_PARAM1) as FavoriteEntity
        }
    }

    var binding: FragmentDetailFavoriteBinding? = null
    val viewModel: MyViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_detail_favorite, container, false)
        binding?.favorite = category
        binding?.lifecycleOwner = this
        // Inflate the layout for this fragment
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val detailAdapter = MyDetailFavoriteAdapter() {

        }
        binding?.detailRec?.apply {
            adapter = detailAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
        category?.let {
            viewModel.getListDetailFavoriteById(it.idProduct)
            binding?.apply {
                imageFavorite.setImageResource(R.drawable.ic_favorite_red)
                imageFavorite.setOnClickListener { v ->
                    viewModel.deleteFavorite(it.idProduct)
                    activity?.apply {
                        supportFragmentManager.popBackStack()
                    }
                }
            }
        }
        viewModel.listFavoriteDetail.observe(requireActivity()) {
            it?.let {
                detailAdapter.submitList(it)
            }
        }

    }

    companion object {
        const val TAG = "DetailFragment"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: FavoriteEntity) =
            DetailFavoriteFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, param1)
                }
            }
    }
}