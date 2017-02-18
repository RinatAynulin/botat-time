package com.aynulin.botat.time.repository;

import com.aynulin.botat.time.model.Forecast;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

/**
 * @Aynulin on 18.02.2017.
 */
public interface ForecastRepository extends JpaRepository<Forecast, Long> {
    Collection<Forecast> getLastForecastsForCity(Long cityId, Pageable pageable);
}
