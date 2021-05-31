package com.panneer.moviecatalogservice.service;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.panneer.moviecatalogservice.model.CatalogItem;
import com.panneer.moviecatalogservice.model.Movie;
import com.panneer.moviecatalogservice.model.RatingItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieInfoService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getCatalogItemFallBack", threadPoolKey = "movie-info-pool",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "4"),
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50")
            }, threadPoolProperties = {
            @HystrixProperty(name = "coreSize", value = "30"),
            @HystrixProperty(name = "maxQueueSize", value = "10")
    })
    public CatalogItem getCatalogItem(RatingItem ratingItem) {
        Movie movie = restTemplate.getForObject("http://movie-info-service/movies/"+ ratingItem.getMovieId(), Movie.class);
        return new CatalogItem(movie.getName(), "Descripe transformers", ratingItem.getRating());
    }

    public CatalogItem getCatalogItemFallBack(RatingItem ratingItem) {
        return new CatalogItem("No movieName", "No Desd", ratingItem.getRating());
    }

}
