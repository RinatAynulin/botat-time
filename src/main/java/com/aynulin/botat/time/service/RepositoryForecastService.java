package com.aynulin.botat.time.service;

import com.aynulin.botat.time.model.City;
import com.aynulin.botat.time.model.Forecast;
import com.aynulin.botat.time.repository.CityRepository;
import com.aynulin.botat.time.repository.ForecastRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * @Aynulin on 18.02.2017.
 */

@Service
public class RepositoryForecastService implements ForecastService {
    private ForecastRepository forecastRepository;
    private CityRepository cityRepository;

    @Autowired
    public RepositoryForecastService(ForecastRepository forecastRepository, CityRepository cityRepository) {
        this.forecastRepository = forecastRepository;
        this.cityRepository = cityRepository;
    }

    @Override
    public List<Forecast> getNextFiveDaysForecast(Long cityId) {
        Pageable limit = new PageRequest(0, 5);
        return forecastRepository.findByCityId(cityId, limit);
    }

    @Override
    public void updateNextFiveDays(List<Forecast> forecasts, long cityId) {
        City city = cityRepository.findOne(cityId);
        for (Forecast forecast: getNextFiveDaysForecast(cityId)) {
            forecastRepository.delete(forecast);
        }

        for (Forecast forecast: forecasts) {
            forecast.setCity(city);
            forecastRepository.save(forecast);
        }
    }
}
