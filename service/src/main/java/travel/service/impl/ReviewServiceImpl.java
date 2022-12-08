package travel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import travel.domain.Review;
import travel.persistence.ReviewRepository;
import travel.persistence.UserRepository;
import travel.persistence.dto.ReviewDto;
import travel.service.ReviewService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    private ReviewDto convertReviewToDto(Review review) {
        ReviewDto reviewDTO = new ReviewDto();
        reviewDTO.setId(review.getId());
        reviewDTO.setRating(review.getRating());
        reviewDTO.setComment(review.getComment());
        reviewDTO.setUserId(review.getUser().getId());
        reviewDTO.setAttractionId(review.getAttraction().getId());
        return reviewDTO;
    }

    @Override
    public List<Review> getReviews(long attractionId) {
        return reviewRepository.findAll().stream().filter(r ->
                        r.getAttraction().getId() == attractionId)
                .collect(Collectors.toUnmodifiableList());
    }
}
