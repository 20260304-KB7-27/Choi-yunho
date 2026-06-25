package org.scoula.weather.controller;

import org.scoula.weather.dto.WeatherDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
@RequestMapping("/weather")
@PropertySource({"classpath:/application.properties"})
public class WeatherController {

    @Value("${weather.url}")
    private String URL;
    @Value("${weather.api_key}")
    private String API_KEY;
    @Value("${weather.icon_url}")
    private String ICON_URL;

    @GetMapping({"", "/{city}"})
    public String weather(Model model, @PathVariable(value = "city", required = false) String city) {
        city = city == null ? "seoul" : city;

        // RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        String url = UriComponentsBuilder.fromHttpUrl(URL)
                .queryParam("q", city)
                .queryParam("units", "metric") // 섭씨온도
                .queryParam("APPID", API_KEY)
                .queryParam("lang", "kr")
                .toUriString();

        // API 호출 및 응답받기
        WeatherDTO weather = restTemplate.getForObject(url, WeatherDTO.class);

        String iconUrl = ICON_URL.formatted(
                weather.getWeather().get(0).getIcon()
        );

        model.addAttribute("city", city);
        model.addAttribute("weather", weather);
        model.addAttribute("iconUrl", iconUrl);
        return "weather/today";
    }
}
