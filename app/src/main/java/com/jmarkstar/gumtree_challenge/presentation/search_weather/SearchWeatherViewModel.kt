package com.jmarkstar.gumtree_challenge.presentation.search_weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmarkstar.gumtree_challenge.common.coroutines.DispatcherProvider
import com.jmarkstar.gumtree_challenge.domain.doIfFailure
import com.jmarkstar.gumtree_challenge.domain.doIfSuccess
import com.jmarkstar.gumtree_challenge.domain.models.RecentSearchModel
import com.jmarkstar.gumtree_challenge.domain.models.WeatherModel
import com.jmarkstar.gumtree_challenge.domain.usecases.recent_searches.GetLastFiveRecentSearchesUseCase
import com.jmarkstar.gumtree_challenge.domain.usecases.weather.GetWeatherByQueryUseCase
import com.jmarkstar.gumtree_challenge.presentation.common.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchWeatherViewModel @Inject constructor(
    private val getWeatherByQueryUseCase: GetWeatherByQueryUseCase,
    private val getLastFiveRecentSearchesUseCase: GetLastFiveRecentSearchesUseCase,
    private val dispatcherProvider: DispatcherProvider,
    private val savedStateHandle: SavedStateHandle? = null
) : ViewModel() {

    val weather: LiveData<Resource<WeatherModel>>
        get() = _weather

    private val _weather = MutableLiveData<Resource<WeatherModel>>()

    val lastSearches: LiveData<Resource<List<RecentSearchModel>>>
        get() = _lastSearches

    private val _lastSearches = MutableLiveData<Resource<List<RecentSearchModel>>>()

    fun searchWeather(query: String) {
        // TODO: Do some validations

        _weather.postValue(Resource.Loading())

        viewModelScope.launch(dispatcherProvider.IO) {

            val result = getWeatherByQueryUseCase.invoke(query)
            result.doIfSuccess {
                _weather.postValue(Resource.Success(it))
            }
            result.doIfFailure {
                _weather.postValue(Resource.Error(it?.message))
            }
        }
    }

    fun loadFiveLastSearches() {
        _lastSearches.postValue(Resource.Loading())

        viewModelScope.launch(dispatcherProvider.IO) {

            val result = getLastFiveRecentSearchesUseCase.invoke()
            result.doIfSuccess {
                _lastSearches.postValue(Resource.Success(it))
            }
            result.doIfFailure {
                _lastSearches.postValue(Resource.Error(it?.message))
            }
        }
    }
}
