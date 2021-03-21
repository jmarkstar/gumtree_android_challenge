package com.jmarkstar.gumtree_challenge.presentation.search_weather

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.jmarkstar.gumtree_challenge.R
import com.jmarkstar.gumtree_challenge.databinding.FragmentSearchWeatherBinding
import com.jmarkstar.gumtree_challenge.domain.models.WeatherModel
import com.jmarkstar.gumtree_challenge.presentation.common.BaseFragment
import com.jmarkstar.gumtree_challenge.presentation.common.Resource
import com.jmarkstar.gumtree_challenge.presentation.common.extensions.invisible
import com.jmarkstar.gumtree_challenge.presentation.common.extensions.visible
import com.jmarkstar.gumtree_challenge.presentation.recent_searches.RecentSearchAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchWeatherFragment : BaseFragment<FragmentSearchWeatherBinding>() {

    @Inject
    lateinit var recentSearchAdapter: RecentSearchAdapter

    override fun layoutId() = R.layout.fragment_search_weather

    override fun screenTitleId() = R.string.search_screen_title

    private val viewModel: SearchWeatherViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recentSearchAdapter.onItemClick = {
            viewModel.searchWeather(it.query)
        }

        binding.apply {

            viewModel.lastSearches.observe(viewLifecycleOwner) {
                when (it) {
                    is Resource.Success -> {
                        rvLastSearches.visible()
                        tvLastSearchesMessage.invisible()
                        pgLoadingLastSearches.invisible()
                        tvSeeAllRecentSearches.visible()

                        recentSearchAdapter.setItems(it.data)
                    }

                    is Resource.Error -> {
                        rvLastSearches.invisible()
                        pgLoadingLastSearches.invisible()
                        tvLastSearchesMessage.visible()

                        // TODO: create a common error handler for generics exception for the whole project.
                        if (it.throwable is IllegalStateException) {
                            tvSeeAllRecentSearches.invisible()
                            tvLastSearchesMessage.text = getString(R.string.search_screen_last_searches_empty_message)
                        } else
                            tvLastSearchesMessage.text = it.message
                    }

                    is Resource.Loading -> {
                        tvSeeAllRecentSearches.invisible()
                        rvLastSearches.invisible()
                        tvLastSearchesMessage.invisible()
                        pgLoadingLastSearches.visible()
                    }
                }
            }

            viewModel.weather.observe(viewLifecycleOwner) {
                when (it) {
                    is Resource.Success -> processWeatherSuccess(it.data)

                    is Resource.Error -> processWeatherError(it.message)

                    is Resource.Loading -> processLoading()
                }
            }

            rvLastSearches.adapter = recentSearchAdapter

            btnSearch.setOnClickListener {
                // TODO: Add search by zip code feature
                // val searchByCityName = rbCityName.isChecked
                viewModel.searchWeather(etSearch.text.toString())
            }

            tvSeeAllRecentSearches.setOnClickListener {
                findNavController().navigate(R.id.action_see_all_recent_searches)
            }
        }

        viewModel.loadFiveLastSearches()
    }

    private fun processLoading() = binding.apply {
        weatherGroup.invisible()
        tvMessage.invisible()
        pgSearching.visible()
    }

    private fun processWeatherSuccess(weather: WeatherModel?) = binding.apply {
        pgSearching.invisible()
        tvMessage.invisible()
        weatherGroup.visible()

        weatherIcon.setImageURI(weather?.iconUrl)
        weatherTemperature.text = String.format(getString(R.string.search_screen_weather_temperature), weather?.temperature)
        weatherFeelsLike.text = String.format(getString(R.string.search_screen_weather_feels_like), weather?.feelsLike)
        weatherHumidity.text = String.format(getString(R.string.search_screen_weather_humidity), weather?.humidity)
        weatherDescription.text = String.format(getString(R.string.search_screen_weather_description), weather?.name, weather?.description)
        weatherPlace.text = String.format(getString(R.string.search_screen_weather_place), weather?.place, weather?.country)

        viewModel.loadFiveLastSearches()
    }

    private fun processWeatherError(message: String?) = binding.apply {
        pgSearching.invisible()
        tvMessage.visible()
        weatherGroup.invisible()
        tvMessage.text = message
    }
}
