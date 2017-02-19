package com.aynulin.botat.time.service;

import com.aynulin.botat.time.model.Forecast;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @Aynulin on 18.02.2017.
 */

@Service
public class ApiForecastService {
    private static final String PROPERTY_NAME_OWM_API_KEY = "openweathermap.api.key";
    private static final String BASE_OWM_URL = "http://api.openweathermap.org/data/2.5/forecast?id=";
    private static final String CELSIUS_UNITS = "&units=metric";
    private static final Long DEFAULT_CITY_ID = 524901L; // MOSCOW
    private static final String DATE_FIELD_NAME = "dt";
    private static final String MAIN_FIELD_NAME = "main";
    private static final String TEMP_DAY_FIELD_NAME = "temp";
    private static final String TEMP_MAX_FIELD_NAME = "temp_max";
    private static final String TEMP_MIN_FIELD_NAME = "temp_min";
    private static final String PRESSURE_FIELD_NAME = "pressure";
    private static final String HUMIDITY_FIELD_NAME = "humidity";
    private static final String WEATHER_FIELD_NAME = "weather";
    private static final String WEATHER_ID_FIELD_NAME = "id";
    private static final String WEATHER_MAIN_FIELD_NAME = "main";
    private static final String WEATHER_DESCRIPTION_FIELD_NAME = "description";


    @Resource
    private Environment env;


    public List<Forecast> getNextFiveDaysForecast(Long cityId) {
        List<Forecast> result = new ArrayList<>();
        HttpClient httpClient = HttpClientBuilder.create().build();
        String url = BASE_OWM_URL + DEFAULT_CITY_ID + "&appid=" +
                env.getRequiredProperty(PROPERTY_NAME_OWM_API_KEY) +
                CELSIUS_UNITS;

        HttpPost post = new HttpPost(url);
        try {
            StringBuilder jsonBuilder = new StringBuilder();
            HttpResponse response = httpClient.execute(post);
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String currentString;
            while ((currentString = reader.readLine()) != null) {
                jsonBuilder.append(currentString);
            }
            ObjectMapper mapper = new ObjectMapper();
            Iterator<JsonNode> nodes = mapper.readTree(jsonBuilder.toString()).get("list").getElements();
            while (nodes.hasNext()) {
                JsonNode node = nodes.next();
                Forecast forecast = createForecastFromNode(node);
                result.add(forecast);
                moveToNextDay(nodes); // get only one node per day
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void moveToNextDay(Iterator<JsonNode> nodes) {
        for (int i = 0; i < 7; i++) {
            try {
                nodes.next();
            } catch (NoSuchElementException e) {
                break;
            }
        }
    }


    private Forecast createForecastFromNode(JsonNode node) {
        Forecast forecast = new Forecast();
        forecast.setDate(node.get(DATE_FIELD_NAME).asLong());

        JsonNode main = node.get(MAIN_FIELD_NAME);
        forecast.setTempDay(main.get(TEMP_DAY_FIELD_NAME).getDecimalValue());
        forecast.setTempMax(main.get(TEMP_MAX_FIELD_NAME).getDecimalValue());
        forecast.setTempMin(main.get(TEMP_MIN_FIELD_NAME).getDecimalValue());
        forecast.setHumidity(main.get(HUMIDITY_FIELD_NAME).getDecimalValue());
        forecast.setPressure(main.get(PRESSURE_FIELD_NAME).getDecimalValue());

        JsonNode weather = node.get(WEATHER_FIELD_NAME).get(0);
        forecast.setWeatherId(weather.get(WEATHER_ID_FIELD_NAME).getIntValue());
        forecast.setWeatherMain(weather.get(WEATHER_MAIN_FIELD_NAME).asText());
        forecast.setWeatherDescription(weather.get(WEATHER_DESCRIPTION_FIELD_NAME).asText());

        return forecast;
    }

}
