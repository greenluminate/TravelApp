package travel.service;

import travel.domain.Review;

import java.util.List;

public interface ReviewService {
    List<Review> getReviews(long attractionId);
}
