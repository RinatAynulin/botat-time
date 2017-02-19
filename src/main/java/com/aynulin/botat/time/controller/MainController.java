package com.aynulin.botat.time.controller;

import com.aynulin.botat.time.model.Forecast;
import com.aynulin.botat.time.service.ApiForecastService;
import com.aynulin.botat.time.service.ForecastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @Aynulin on 19.02.2017.
 */

@Controller
@RequestMapping("/")
public class MainController {
    private static final String MAIN_PAGE_JSP_NAME = "main";
    private static final Long DEFAULT_CITY_ID = 524901L; // MOSCOW

    private final ForecastService forecastService;
    private final ApiForecastService apiForecastService;

    @Autowired
    public MainController(ForecastService forecastService, ApiForecastService apiForecastService) {
        this.forecastService = forecastService;
        this.apiForecastService = apiForecastService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getMainPage(Model model) {
        forecastService.updateNextFiveDays(apiForecastService.getNextFiveDaysForecast(DEFAULT_CITY_ID), DEFAULT_CITY_ID); //fixme remove
        List<Forecast> forecasts = forecastService.getNextFiveDaysForecast(DEFAULT_CITY_ID);
        model.addAttribute("forecasts", forecasts);
        return MAIN_PAGE_JSP_NAME;
    }
}
