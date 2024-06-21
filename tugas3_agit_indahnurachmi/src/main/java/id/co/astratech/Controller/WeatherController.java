package id.co.astratech.Controller;

import id.co.astratech.Service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/city")
    public ResponseEntity<?> getWeatherByCity(@RequestParam String city) {
        Map<String, Object> response = weatherService.getWeatherByCity(city);
        return ResponseEntity.ok(response);
    }

}
