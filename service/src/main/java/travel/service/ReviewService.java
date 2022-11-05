package travel.service;

import travel.domain.Review;
import travel.persistence.dto.ReviewDto;

import java.util.List;

public interface ReviewService {
    Review findReviewById(long id);

    List<ReviewDto> findAllReviews();
    List<ReviewDto> findUserReviews(long userId);
}
