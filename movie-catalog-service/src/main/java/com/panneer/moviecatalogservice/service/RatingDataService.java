package com.panneer.moviecatalogservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.panneer.moviecatalogservice.model.RatingItem;
import com.panneer.moviecatalogservice.model.UserRatingItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class RatingDataService {
    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getUserRatingFallBack", threadPoolKey = "rating-data-pool",
      commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
              @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "4"),
              @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
              @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50")
      }, threadPoolProperties = {
            @HystrixProperty(name = "coreSize", value = "30"),
            @HystrixProperty(name = "maxQueueSize", value = "10")
    })
    public UserRatingItem getForUserRating(String userId) {
        return restTemplate.getForObject("http://ratings-data-service/ratingsData/users/" + userId, UserRatingItem.class);
    }

    public UserRatingItem getUserRatingFallBack(String userId) {
        UserRatingItem userRatingItem = new UserRatingItem();
        userRatingItem.setRatingItemList(Arrays.asList(
                new RatingItem("No movieId", 0)
        ));
        return userRatingItem;
    }
}
