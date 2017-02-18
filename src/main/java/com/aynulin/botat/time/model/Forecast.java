package com.aynulin.botat.time.model;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @Aynulin on 18.02.2017.
 */

@Entity
@Table(name = "forecast")
public class Forecast {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "date")
    private int date; // unix time

    @Column(name = "temp_day")
    private BigDecimal tempDay; // day temperature

    @Column(name = "temp_min")
    private BigDecimal tempMin; // min daily temperature

    @Column(name = "temp_max")
    private BigDecimal tempMax; // max daily temperature

    @Column(name = "humidity")
    private BigDecimal humidity; // average humidity

    @Column(name = "pressure")
    private BigDecimal pressure; // atmospheric pressure on the sea level, hPa

    @Column(name = "weather_id") // weather condition id, 6xx stands for snow
    private int weatherId;

    @Column(name = "weather_main")
    private String weatherMain; // group of weather parameters

    @Column(name = "weather_description")
    private String weatherDescription; // weather condition within the group

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    public Forecast() {
    }

    public Long getId() {
        return id;
    }

    public int getDate() {
        return date;
    }

    public BigDecimal getTempDay() {
        return tempDay;
    }

    public BigDecimal getTempMin() {
        return tempMin;
    }

    public BigDecimal getTempMax() {
        return tempMax;
    }

    public BigDecimal getHumidity() {
        return humidity;
    }

    public BigDecimal getPressure() {
        return pressure;
    }

    public int getWeatherId() {
        return weatherId;
    }

    public String getWeatherMain() {
        return weatherMain;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public City getCity() {
        return city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Forecast forecast = (Forecast) o;

        if (date != forecast.date) return false;
        if (weatherId != forecast.weatherId) return false;
        if (id != null ? !id.equals(forecast.id) : forecast.id != null) return false;
        if (tempDay != null ? !tempDay.equals(forecast.tempDay) : forecast.tempDay != null) return false;
        if (tempMin != null ? !tempMin.equals(forecast.tempMin) : forecast.tempMin != null) return false;
        if (tempMax != null ? !tempMax.equals(forecast.tempMax) : forecast.tempMax != null) return false;
        if (humidity != null ? !humidity.equals(forecast.humidity) : forecast.humidity != null) return false;
        if (pressure != null ? !pressure.equals(forecast.pressure) : forecast.pressure != null) return false;
        if (weatherMain != null ? !weatherMain.equals(forecast.weatherMain) : forecast.weatherMain != null)
            return false;
        if (weatherDescription != null ? !weatherDescription.equals(forecast.weatherDescription) : forecast.weatherDescription != null)
            return false;
        return city != null ? city.equals(forecast.city) : forecast.city == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + date;
        result = 31 * result + (tempDay != null ? tempDay.hashCode() : 0);
        result = 31 * result + (tempMin != null ? tempMin.hashCode() : 0);
        result = 31 * result + (tempMax != null ? tempMax.hashCode() : 0);
        result = 31 * result + (humidity != null ? humidity.hashCode() : 0);
        result = 31 * result + (pressure != null ? pressure.hashCode() : 0);
        result = 31 * result + weatherId;
        result = 31 * result + (weatherMain != null ? weatherMain.hashCode() : 0);
        result = 31 * result + (weatherDescription != null ? weatherDescription.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Forecast{" +
                "id=" + id +
                ", date=" + date +
                ", tempDay=" + tempDay +
                ", tempMin=" + tempMin +
                ", tempMax=" + tempMax +
                ", humidity=" + humidity +
                ", pressure=" + pressure +
                ", weatherId=" + weatherId +
                ", weatherMain='" + weatherMain + '\'' +
                ", weatherDescription='" + weatherDescription + '\'' +
                '}';
    }
}
