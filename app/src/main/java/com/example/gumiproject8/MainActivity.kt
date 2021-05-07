package com.example.gumiproject8

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.gumiproject8.databinding.ActivityMainBinding
import com.example.gumiproject8.ui.characters.CharactersFragment
import com.example.gumiproject8.ui.comics.ComicsFragment
import com.example.gumiproject8.ui.favorite.FavoritesFragment
import com.example.gumiproject8.utils.Constants
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding?.root)
        init()
    }

    private fun init(){

        val list = mutableListOf<Fragment>()
        list.add(CharactersFragment.newInstance(Constants.CHARACTER))
        list.add(ComicsFragment.newInstance(Constants.COMIC))
        list.add(FavoritesFragment.newInstance("Favorites", ""))

        val listName = mutableListOf<String>()
        listName.add("Characters")
        listName.add("Comics")
        listName.add("Favorites")

        val listImgaes = listOf(R.drawable.ic_character, R.drawable.ic_comic_book, R.drawable.ic_favorite)

        binding?.apply {

            //chặn user vuốt ngang viewpager
            viewPager.isUserInputEnabled = false

            viewPager.adapter = ViewPagerAdapter(supportFragmentManager, lifecycle, list)
            TabLayoutMediator(tabLayout, viewPager){ tab, pos ->
                tab.icon = ContextCompat.getDrawable(this@MainActivity, listImgaes[pos])
                tab.text = listName[pos]
            }.attach()
        }
    }

}