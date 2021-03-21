package com.jmarkstar.gumtree_challenge.presentation.recent_searches

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmarkstar.gumtree_challenge.common.coroutines.DispatcherProvider
import com.jmarkstar.gumtree_challenge.domain.doIfFailure
import com.jmarkstar.gumtree_challenge.domain.doIfSuccess
import com.jmarkstar.gumtree_challenge.domain.models.RecentSearchModel
import com.jmarkstar.gumtree_challenge.domain.usecases.recent_searches.DeleteAllRecentSearchesUseCase
import com.jmarkstar.gumtree_challenge.domain.usecases.recent_searches.GetAllRecentSearchesUseCase
import com.jmarkstar.gumtree_challenge.presentation.common.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

class RecentSearchesViewModel @Inject constructor(
    private val getAllRecentSearchesUseCase: GetAllRecentSearchesUseCase,
    private val deleteAllRecentSearchesUseCase: DeleteAllRecentSearchesUseCase,
    private val dispatcherProvider: DispatcherProvider,
    private val savedStateHandle: SavedStateHandle? = null
) : ViewModel() {

    val recentSearches: LiveData<Resource<List<RecentSearchModel>>>
        get() = _recentSearches

    private val _recentSearches = MutableLiveData<Resource<List<RecentSearchModel>>>()

    val deleteSearches: LiveData<Resource<Boolean>>
        get() = _deleteSearches

    private val _deleteSearches = MutableLiveData<Resource<Boolean>>()

    fun loadAllSearches() {
        _recentSearches.postValue(Resource.Loading())

        viewModelScope.launch(dispatcherProvider.IO) {
            val result = getAllRecentSearchesUseCase.invoke()
            result.doIfSuccess {
                _recentSearches.postValue(Resource.Success(it))
            }
            result.doIfFailure {
                _recentSearches.postValue(Resource.Error(it?.message))
            }
        }
    }

    fun deleteAll() {
        _deleteSearches.postValue(Resource.Loading())

        viewModelScope.launch(dispatcherProvider.IO) {
            val result = deleteAllRecentSearchesUseCase.invoke()
            result.doIfSuccess {
                _deleteSearches.postValue(Resource.Success(it))
            }
            result.doIfFailure {
                _deleteSearches.postValue(Resource.Error(it?.message))
            }
        }
    }
}
