package com.example.gumiproject8.ui.detail

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
import com.example.gumiproject8.model.favorite.FavoriteEntity
import com.example.gumiproject8.model.favorite.FavoriteListDetail
import com.example.gumiproject8.model.mymodel.BaseModel
import com.example.gumiproject8.model.mymodel.ProductModel
import com.example.gumiproject8.ui.MyAdapter
import com.example.gumiproject8.ui.MyDetailAdapter
import com.example.gumiproject8.ui.MyViewModel
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
class DetailFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var category: ProductModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            category = it.getSerializable(ARG_PARAM1) as ProductModel
        }
    }

    var binding: FragmentDetailBinding? = null
    val detailViewModel: MyViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        binding?.detail = detailViewModel
        binding?.lifecycleOwner = this
        // Inflate the layout for this fragment
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //check loại comic or character or favorite
        category?.let {
            detailViewModel.getDataDetail(it)
            detailViewModel.progressLiveData.observe(requireActivity()) {
                it?.let {
                    if (it) {
                        binding?.progressBar?.visible()
                        binding?.detailLayout?.gone()
                    } else {
                        binding?.progressBar?.gone()
                        binding?.detailLayout?.visible()
                    }
                }
            }
        }
        val detailAdapter = MyDetailAdapter() {

        }
        binding?.detailRec?.apply {
            adapter = detailAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }

        detailViewModel.status.observe(requireActivity()) {
            it?.let { check ->
                binding?.apply {
                    if (check) {
                        imageFavorite.setImageResource(R.drawable.ic_favorite_red)
                    } else {
                        imageFavorite.setImageResource(R.drawable.ic_favorite)
                    }
                }
            }
        }
        detailViewModel.listDetail.observe(requireActivity()) {
            detailAdapter.submitList(it)
        }
        binding?.imageFavorite?.setOnClickListener {
            val baseModel = detailViewModel.detail.value!!
            val list = mutableListOf<FavoriteListDetail>()
            detailViewModel.listDetail.value?.forEach {
                list.add(FavoriteListDetail(0, baseModel.id, it.myName, it.myImage))
            }

            val check = detailViewModel.status.value!!
            if (check) {
                detailViewModel.deleteFavorite(baseModel.id)
            } else {
                detailViewModel.insertFavorite(
                    FavoriteEntity(
                        0,
                        baseModel.id,
                        baseModel.myName,
                        baseModel.myListDetail,
                        baseModel.myDes,
                        baseModel.myImage,
                    ), list
                )
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
        fun newInstance(param1: ProductModel) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, param1)
                }
            }
    }
}