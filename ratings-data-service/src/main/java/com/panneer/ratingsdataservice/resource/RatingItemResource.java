package com.panneer.ratingsdataservice.resource;

import com.panneer.ratingsdataservice.model.RatingItem;
import com.panneer.ratingsdataservice.model.UserRatingItem;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratingsData")
public class RatingItemResource {

    @GetMapping("/{movieId}")
    public RatingItem getRatingItem(@PathVariable("movieId") String movieId) {
        return new RatingItem(movieId,4);
    }

    @GetMapping("/users/{userId}")
    public UserRatingItem getRatingItemList(@PathVariable("userId") String userId) {
      UserRatingItem userRatingItem = new UserRatingItem();
      userRatingItem.setRatingItemList(Arrays.asList( new RatingItem("1234",4),
              new RatingItem("abc", 8),
              new RatingItem("Anabelle", 5)));

      return userRatingItem;
    }
}
