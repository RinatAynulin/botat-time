package com.aynulin.botat.time.service;

import com.aynulin.botat.time.model.Forecast;
import com.aynulin.botat.time.repository.ForecastRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * @Aynulin on 18.02.2017.
 */

@Service
public class RepositoryForecastService implements ForecastService {
    private ForecastRepository forecastRepository;

    @Autowired
    public RepositoryForecastService(ForecastRepository forecastRepository) {
        this.forecastRepository = forecastRepository;
    }

    @Override
    public Collection<Forecast> getNextFourteenDaysForecast(Long cityId) {
        Pageable limit = new PageRequest(0, 14);
        return forecastRepository.getLastForecastsForCity(cityId, limit);
    }
}
