package com.aynulin.botat.time.scheduler;

import com.aynulin.botat.time.service.ApiForecastService;
import com.aynulin.botat.time.service.ForecastService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

/**
 * @Aynulin on 19.02.2017.
 */

@Component
public class Job extends QuartzJobBean {
    private static final Long DEFAULT_CITY_ID = 524901L; // MOSCOW
    @Autowired
    private ForecastService forecastService;
    @Autowired
    private ApiForecastService apiForecastService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        forecastService.updateNextFiveDays(apiForecastService.getNextFiveDaysForecast(DEFAULT_CITY_ID),
                DEFAULT_CITY_ID);
    }
}
