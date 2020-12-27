package com.example.learnenglish.ui.vocabulary.search

import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learnenglish.R
import com.example.learnenglish.database.SearchDatabase
import com.example.learnenglish.model.VocabularyItem
import kotlinx.android.synthetic.main.activity_voca_search.*
import java.util.*


class VocaSearchActivity : AppCompatActivity() {

    private lateinit var adapterSearch: SearchAdapter
    private lateinit var searchDatabase: SearchDatabase
    private var itemList = ArrayList<VocabularyItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_voca_search)

        searchDatabase = SearchDatabase(this)

        adapterSearch = SearchAdapter(mutableListOf())
        rvSearchResult.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = adapterSearch
        }

        svVoca.requestFocus()
        svVoca.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                itemList = if (newText == "") {
                    ArrayList<VocabularyItem>()
                } else {
                    searchDatabase.getVocabularyItemByName(svVoca.query.toString()) as ArrayList<VocabularyItem>
                }
                adapterSearch.setData(itemList)
                return false
            }
        })
    }
}