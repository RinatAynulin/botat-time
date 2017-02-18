package com.aynulin.botat.time.service;

import com.aynulin.botat.time.model.Forecast;

import java.util.Collection;

/**
 * @Aynulin on 18.02.2017.
 */
public interface ForecastService {
    Collection<Forecast> getNextFourteenDaysForecast(Long cityId);
}
