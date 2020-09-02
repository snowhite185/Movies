package com.example.movies.ui

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.movies.data.MovieRepository
import com.example.movies.data.SearchItemModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Anusha K on 30-08-2020.
 */
class SearchViewModel @Inject constructor(
    application: Application,
    private var repository: MovieRepository
) :
    AndroidViewModel(application) {

    private val searchItemList = ArrayList<SearchItemModel>()
    val searchResultLiveData = MutableLiveData<ArrayList<SearchItemModel>>()
    var errorView = ObservableBoolean(false)

    fun onSearchTextChange(text: CharSequence, start: Int, before: Int, count: Int) {
        if (text.isEmpty()) {
            searchResultLiveData.postValue(null)
            return
        }
        viewModelScope.launch {
            var response = repository.getSearchResults(text.toString())
            errorView.set(response == null)
            val itemList = response?.searchResult?.map {
                val itemModel = SearchItemModel()
                itemModel.title = it.title
                itemModel.imdbID = it.imdbID
                itemModel.type = it.type
                itemModel.year = it.year
                itemModel
            }
            itemList?.let {
                searchItemList.clear()
                searchItemList.addAll(it)
                searchResultLiveData.postValue(searchItemList)
            }

        }
    }
}