package com.example.learnenglish.database

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

object UserManager {

    private const val PREF_NAME = "com.example.learningenglish.user"
    private const val MY_POINT = "my_point"

    private fun getPrefs(context: Context): SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun setMyPoint(context: Context, point: Int) {
        getPrefs(context).edit { putInt(MY_POINT, point) }
    }

    fun getMyPoint(context: Context): Int = getPrefs(context).getInt(MY_POINT, 0)
}