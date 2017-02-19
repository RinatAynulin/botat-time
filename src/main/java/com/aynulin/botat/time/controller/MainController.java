package com.aynulin.botat.time.controller;

import com.aynulin.botat.time.model.Forecast;
import com.aynulin.botat.time.service.ApiForecastService;
import com.aynulin.botat.time.service.ForecastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.*;

/**
 * @Aynulin on 19.02.2017.
 */

@Controller
@RequestMapping("/")
public class MainController {
    private static final String MAIN_PAGE_SNOW_TODAY_JSP_NAME = "snow_today_main";

    private static final Long DEFAULT_CITY_ID = 524901L; // MOSCOW
    private static final String MAIN_PAGE_SNOW_TOMORROW_JSP_NAME = "snow_tomorrow_main";
    private static final String MAIN_PAGE_SNOW_NEXT_DAYS_JSP_NAME = "snow_next_days_main";
    private static final String MAIN_PAGE_NO_SNOW_JSP_NAME = "no_snow_main";

    private final ForecastService forecastService;

    @Autowired
    public MainController(ForecastService forecastService, ApiForecastService apiForecastService) {
        this.forecastService = forecastService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getMainPage(Model model) {
        List<Forecast> forecasts = forecastService.getNextFiveDaysForecast(DEFAULT_CITY_ID);
        List<String> days = new ArrayList<>(Arrays.asList("Сегодня", "Завтра", "Послезавтра"));
        LinkedHashMap<String, Forecast> data = new LinkedHashMap<>();
        for (int i = 0; i < 3; i++) {
            data.put(days.get(i), forecasts.get(i));
        }
        model.addAttribute("data", data);
        if (isSnowNextFiveDays(forecasts)) {
            if (isSnowToday(forecasts)) {
                return MAIN_PAGE_SNOW_TODAY_JSP_NAME;
            } else if (isSnowTomorrow(forecasts)) {
                return MAIN_PAGE_SNOW_TOMORROW_JSP_NAME;
            } else {
                return MAIN_PAGE_SNOW_NEXT_DAYS_JSP_NAME;
            }
        } else {
            return MAIN_PAGE_NO_SNOW_JSP_NAME;
        }
    }

    private boolean isSnowToday(List<Forecast> forecasts) {
        return String.valueOf(forecasts.get(0).getWeatherId()).startsWith("6"); // 6xx code fore snow
    }

    private boolean isSnowTomorrow(List<Forecast> forecasts) {
        return String.valueOf(forecasts.get(1).getWeatherId()).startsWith("6"); // 6xx code fore snow
    }

    private boolean isSnowNextFiveDays(List<Forecast> forecasts) {
        for (Forecast forecast: forecasts) {
            if (String.valueOf(forecast.getWeatherId()).startsWith("6")) { // 6xx code fore snow
                return true;
            }
        }
        return false;
    }
}
