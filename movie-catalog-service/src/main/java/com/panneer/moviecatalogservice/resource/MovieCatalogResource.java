package com.panneer.moviecatalogservice.resource;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.panneer.moviecatalogservice.model.CatalogItem;
import com.panneer.moviecatalogservice.model.Movie;
import com.panneer.moviecatalogservice.model.RatingItem;
import com.panneer.moviecatalogservice.model.UserRatingItem;
import com.panneer.moviecatalogservice.service.MovieInfoService;
import com.panneer.moviecatalogservice.service.RatingDataService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RatingDataService ratingDataService;

    @Autowired
    private MovieInfoService movieInfoService;

    @GetMapping("/{userId}")
    public List<CatalogItem> getCatalog (@PathVariable("userId") String userId) {

        UserRatingItem userRatingItem = ratingDataService.getForUserRating(userId);
        return userRatingItem.getRatingItemList().stream().map(ratingItem -> movieInfoService.getCatalogItem(ratingItem)).collect(Collectors.toList());

    }

}
