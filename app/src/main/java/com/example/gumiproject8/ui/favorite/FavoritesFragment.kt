package com.example.gumiproject8.ui.favorite

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gumiproject8.R
import com.example.gumiproject8.ViewPagerAdapter
import com.example.gumiproject8.databinding.FragmentFavoritesBinding
import com.example.gumiproject8.model.favorite.FavoriteEntity
import com.example.gumiproject8.ui.MyViewModel
import com.example.gumiproject8.ui.characters.CharactersFragment
import com.example.gumiproject8.ui.comics.ComicsFragment
import com.example.gumiproject8.ui.detail.DetailFragment
import com.example.gumiproject8.ui.favorite.character.FavoriteCharacterFragment
import com.example.gumiproject8.ui.favorite.comic.FavoriteComicFragment
import com.example.gumiproject8.utils.Constants
import com.example.gumiproject8.utils.gone
import com.example.gumiproject8.utils.visible
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.android.viewmodel.ext.android.viewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FavoritesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FavoritesFragment : Fragment() {
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

    override fun onResume() {
        super.onResume()
        Toast.makeText(requireContext(), "Favorite resume", Toast.LENGTH_SHORT).show()
    }

    var binding: FragmentFavoritesBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoritesBinding.bind(inflater.inflate(R.layout.fragment_favorites, container, false))
        // Inflate the layout for this fragment
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val list = mutableListOf<Fragment>()
        list.add(FavoriteComicFragment.newInstance(Constants.COMIC_DETAIL_LIST, ""))
        list.add(FavoriteCharacterFragment.newInstance(Constants.CHARACTER_DETAIL_LIST, ""))

        val listName = mutableListOf<String>()
        listName.add("Comics")
        listName.add("Characters")

        binding?.apply {
            activity?.let {
                viewPager.adapter = ViewPagerAdapter(it.supportFragmentManager, lifecycle, list)
            }
            TabLayoutMediator(tabLayout, viewPager){tab, pos ->
                tab.text = listName[pos]
            }.attach()
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FavoritesFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FavoritesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}