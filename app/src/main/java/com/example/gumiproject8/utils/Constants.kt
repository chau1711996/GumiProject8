package com.example.gumiproject8.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment

object Constants {
    const val BASE_URL = "https://gateway.marvel.com/"

    const val PUBLIC_API_KEY = "2ee2e044230f45a7e685175d73399ef4"
    const val PRIVATE_API_KEY = "9982bbea89e3d48182da2195862e68ebc348c795"

    const val FAVORITE_DB_NAME = "favorite_db"
    const val FAVORITE_TABLE_NAME = "favorite_table"
    const val FAVORITE_DETAIL_TABLE_NAME = "favorite_detail_table"
    const val COMIC = "comics"
    const val CHARACTER = "characters"
    const val COMIC_DETAIL_LIST = "Characters"
    const val CHARACTER_DETAIL_LIST = "Comics"
    fun addDialoagFragment(fragment: DialogFragment,tag: String, context: AppCompatActivity){
        context.apply {
            supportFragmentManager
                .beginTransaction()
                .add(fragment, tag)
                .addToBackStack(null)
                .commitAllowingStateLoss()
        }
    }

}