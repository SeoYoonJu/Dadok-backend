package com.example.demo.service.scraping;

import com.example.demo.domain.ScrapedProduct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class ScrapingService {

    @Value("${python.api.url}")
    private String pythonApiUrl;

    private final RestTemplate restTemplate;

    public ScrapingService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public List<ScrapedProduct> getScrapedProducts(String keyword) {
        String url = UriComponentsBuilder.fromHttpUrl(pythonApiUrl)
                .queryParam("keyword", keyword)
                .toUriString();
        System.out.println("URL: " + url);

        List<ScrapedProduct> products = restTemplate.getForObject(url, List.class);
        return products;

    }
}
