package com.example.gumiproject8.ui.favorite.character

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gumiproject8.R
import com.example.gumiproject8.databinding.FragmentFavoriteCharacterBinding
import com.example.gumiproject8.model.mymodel.ProductModel
import com.example.gumiproject8.ui.MyViewModel
import com.example.gumiproject8.ui.detail.DetailFragment
import com.example.gumiproject8.ui.favorite.DetailFavoriteFragment
import com.example.gumiproject8.ui.favorite.FavoriteAdapter
import com.example.gumiproject8.utils.Constants
import org.koin.android.viewmodel.ext.android.viewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FavoriteCharacterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FavoriteCharacterFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    var binding: FragmentFavoriteCharacterBinding? = null
    val myViewModel: MyViewModel by viewModel()

    override fun onResume() {
        super.onResume()
        param1?.let {
            myViewModel.getAllFavoriteByName(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorite_character, container, false)
        binding?.favoriteCharacter = myViewModel
        binding?.lifecycleOwner = this
        // Inflate the layout for this fragment
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val gridLayoutManager = GridLayoutManager(requireContext(), 2)
        val characterAdapter = FavoriteAdapter(){
          activity?.apply {
                supportFragmentManager.beginTransaction()
                    .add(
                        R.id.fragment_container,
                        DetailFavoriteFragment.newInstance(it)
                    )
                    .addToBackStack(null)
                    .commit()
            }
        }
        binding?.let {
            it.characterRec.apply {
                adapter = characterAdapter
                layoutManager = gridLayoutManager
            }
        }

        param1?.let {
            myViewModel.getAllFavoriteByName(it)
        }

        myViewModel.favoriteListLiveData.observe(requireActivity()){
            it?.let {
                characterAdapter.submitList(it)
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FavoriteCharacterFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FavoriteCharacterFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}