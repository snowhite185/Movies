package com.example.movies.ui

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.movies.data.DetailsModel
import com.example.movies.data.MovieRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Anusha K on 01-09-2020.
 */
class DetailsViewModel @Inject constructor(
    application: Application,
    private var repository: MovieRepository
) :
    AndroidViewModel(application) {

    var detailsModel = DetailsModel()
    var detailsLiveData = MutableLiveData<DetailsModel>()
    var taskProgressing = ObservableBoolean(true)
    var emptyView = ObservableBoolean(true)
    var errorView = ObservableBoolean(false)
    fun getMovieDetails(title: String) {
        taskProgressing.set(true)
        viewModelScope.launch {
            val response = repository.getMovieDetails(title)
            emptyView.set(response == null)
            errorView.set(response == null)
            detailsModel.title = response?.title
            detailsModel.writer = response?.writer
            detailsModel.director = response?.director
            detailsModel.genre = formatGenre(response?.genre)
            detailsModel.imdbRating = response?.imdbRating
            detailsModel.plot = response?.plot
            detailsModel.released = formatReleaseDate(response?.released, response?.country)
            detailsModel.year = response?.year
            detailsModel.poster = response?.poster
            detailsModel.duration = getDuration(response?.runtime)
            detailsModel.rated = response?.rated
            detailsLiveData.postValue(detailsModel)
            taskProgressing.set(false)
        }


    }

    private fun getDuration(runtime: String?): String {
        if (runtime != null && runtime != "N/A") {
            val time = runtime.split(" ").map {
                it.trim()
            }
            val hours = (time[0].toInt() / 60).toString() //since both are ints, you get an int

            val minutes = (time[0].toInt() % 60).toString()
            return hours.plus("hr ").plus(minutes).plus("m")
        }
        return ""
    }

    private fun formatReleaseDate(released: String?, country: String?): String {
        return released.plus(", (").plus(country).plus(")")
    }

    private fun formatGenre(genre: String?): String {
        var formattedGenreText = ""
        if (genre != null && genre != "N/A") {
            val list = genre.split(",").map {
                it.trim()
            }
            for (i in list.indices) {
                formattedGenreText = formattedGenreText.plus(list[i])
                if (i != list.size - 1) {
                    formattedGenreText = "$formattedGenreText | "
                }
            }
        }
        return formattedGenreText;
    }
}