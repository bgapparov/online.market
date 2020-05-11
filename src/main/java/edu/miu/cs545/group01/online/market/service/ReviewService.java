package edu.miu.cs545.group01.online.market.service;

import edu.miu.cs545.group01.online.market.domain.Review;
import javassist.NotFoundException;

import java.util.List;

public interface ReviewService {
    List<Review> getCreatedReviews();

    void postReview(long reviewId) throws NotFoundException;

    void declineReview(long reviewId) throws NotFoundException;
}
