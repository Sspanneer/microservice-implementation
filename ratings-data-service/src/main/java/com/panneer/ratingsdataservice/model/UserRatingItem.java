package com.panneer.ratingsdataservice.model;

import java.util.List;

public class UserRatingItem {

    private List<RatingItem> ratingItemList;

    public List<RatingItem> getRatingItemList() {
        return ratingItemList;
    }

    public void setRatingItemList(List<RatingItem> ratingItemList) {
        this.ratingItemList = ratingItemList;
    }
}
