package com.aynulin.botat.time.service;

import com.aynulin.botat.time.model.Forecast;

import java.util.List;

/**
 * @Aynulin on 18.02.2017.
 */
public interface ForecastService {
    List<Forecast> getNextFiveDaysForecast(Long cityId);

    void updateNextFiveDays(List<Forecast> forecasts, long cityId);
}
