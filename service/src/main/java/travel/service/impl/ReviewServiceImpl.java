package travel.service.impl;

import org.springframework.stereotype.Service;
import travel.domain.Review;
import travel.persistence.ReviewRepository;
import travel.persistence.dto.ReviewDto;
import travel.service.ReviewService;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public ReviewServiceImpl() {
    }


    @Override
    public Review findReviewById(long id) {
        return null;
    }

    @Override
    public List<ReviewDto> findAllReviews() {
        return null;
    }
}
