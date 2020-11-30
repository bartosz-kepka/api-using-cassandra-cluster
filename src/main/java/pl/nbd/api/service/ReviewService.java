package pl.nbd.api.service;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pl.nbd.api.dto.ReviewDTO;
import pl.nbd.api.exception.NotFoundException;
import pl.nbd.api.model.Review;
import pl.nbd.api.repository.ReviewRepository;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private static final String NOT_FOUND_MESSAGE_PATTERN = "Review with id {0} not found";

    private final ReviewRepository reviewRepository;
    private final ModelMapper modelMapper;

    public Review addReview(ReviewDTO reviewDTO) {
        Review review = modelMapper.map(reviewDTO, Review.class);
        review.setId(Uuids.timeBased().toString());

        return reviewRepository.insert(review);
    }

    public Review getReview(String id) {
        return reviewRepository.findById(id).orElseThrow(() ->
                new NotFoundException(MessageFormat.format(NOT_FOUND_MESSAGE_PATTERN, id)));
    }

    public List<Review> getReviews() {
        return reviewRepository.findAll();
    }

    public Review updateReview(String id, ReviewDTO reviewDTO) {
        Review review = reviewRepository.findById(id).orElseThrow(() ->
                new NotFoundException(MessageFormat.format(NOT_FOUND_MESSAGE_PATTERN, id)));
        modelMapper.map(reviewDTO, review);

        return reviewRepository.save(review);
    }

    public void deleteReview(String id) {
        if (reviewRepository.existsById(id)) {
            reviewRepository.deleteById(id);
        } else {
            throw new NotFoundException(MessageFormat.format(NOT_FOUND_MESSAGE_PATTERN, id));
        }
    }
}
