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
                if (it is Resource.Success) {
                    recentSearchAdapter.setItems(it.data)
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

        viewModel.loadFiveLastSearches()
    }

    private fun processWeatherError(message: String?) = binding.apply {
        pgSearching.invisible()
        tvMessage.visible()
        weatherGroup.invisible()
        tvMessage.text = message
    }
}
