package lv.venta.controller;

import lv.venta.model.Weather;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WeatherController {

    @GetMapping("/weather")
    public String getWeather(Model model) {
        Weather weather = new Weather();
        weather.setDescription("Sunny");
        weather.setTemperature(25);
        weather.setLastUpdated("June 23, 2024");

        model.addAttribute("weather", weather);
        return "weather-page";
    }
}
