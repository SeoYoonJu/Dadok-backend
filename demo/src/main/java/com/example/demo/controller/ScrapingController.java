package com.example.demo.controller;

import com.example.demo.domain.ScrapedProduct;
import com.example.demo.service.scraping.ScrapingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RequestMapping("/scrape-products")
@RestController
public class ScrapingController {

    private final ScrapingService scrapingService;

    public ScrapingController(ScrapingService scrapingService) {
        this.scrapingService = scrapingService;
    }

    @GetMapping("/{keyword}")
    public List<ScrapedProduct> getScrapedProducts(@PathVariable String keyword) {
        return scrapingService.getScrapedProducts(keyword);
    }
}
