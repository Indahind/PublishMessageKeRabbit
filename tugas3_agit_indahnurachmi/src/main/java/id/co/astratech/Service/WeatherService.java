package id.co.astratech.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class WeatherService {

    private final String API_KEY = "YOUR_API_KEY"; // Ganti dengan API Key Anda
    private final String BASE_URL = "http://api.openweathermap.org/data/2.5/weather";

    @Autowired
    private RestTemplate restTemplate;

    public Map<String, Object> getWeatherByCity(String city) {
        try {
            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                    .queryParam("q", city)
                    .queryParam("appid", API_KEY)
                    .queryParam("units", "metric");

            HttpHeaders headers = new HttpHeaders();
            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<Map> response = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET, entity, Map.class);
            log.info("Output API: {}", response.getBody());

            return response.getBody();

        } catch (Exception e) {
            log.error("Terjadi kesalahan dengan OpenWeatherMap API ", e);
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Exception ketika memanggil API",
                    e
            );
        }
    }
}
