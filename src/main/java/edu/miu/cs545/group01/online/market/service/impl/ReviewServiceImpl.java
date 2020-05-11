package edu.miu.cs545.group01.online.market.service.impl;

import edu.miu.cs545.group01.online.market.domain.Review;
import edu.miu.cs545.group01.online.market.domain.Seller;
import edu.miu.cs545.group01.online.market.domain.enums.ReviewStatus;
import edu.miu.cs545.group01.online.market.domain.enums.UserStatus;
import edu.miu.cs545.group01.online.market.repository.ReviewRepository;
import edu.miu.cs545.group01.online.market.service.ReviewService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Override
    public List<Review> getCreatedReviews() {
        return reviewRepository.findAllByStatusCreated();
    }

    @Override
    public void postReview(long reviewId) throws NotFoundException {
        Review review = reviewRepository.findById(reviewId).orElseThrow(()->new NotFoundException("Seller does not exist"));
        review.setStatus(ReviewStatus.POSTED);
        review = reviewRepository.save(review);
    }

    @Override
    public void declineReview(long reviewId) throws NotFoundException {
        Review review = reviewRepository.findById(reviewId).orElseThrow(()->new NotFoundException("Seller does not exist"));
        review.setStatus(ReviewStatus.DECLINED);
        review = reviewRepository.save(review);
    }
}
