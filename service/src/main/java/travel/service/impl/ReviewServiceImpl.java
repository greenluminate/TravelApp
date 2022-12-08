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
    @Autowired
    private UserRepository userRepository;

    /*public ReviewServiceImpl(ReviewRepository reviewRepository, UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
    }

    public ReviewServiceImpl() {
    }*/


    /*@Override
    public Review findReviewById(long id) {
        return reviewRepository.findById(id).orElse(null);
    }

    @Override
    public List<ReviewDto> findAllReviews() {
        return reviewRepository.findAll().stream()
                .map(this::convertReviewToDto).collect(Collectors.toList());
    }

    @Override
    public List<ReviewDto> findUserReviews(long userId) {
        return reviewRepository.findAll().stream()
                .filter(r -> r.getUser().getId() == userId).collect(Collectors.toList())
                .stream().map(this::convertReviewToDto).collect(Collectors.toList());
    }*/

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
