package pl.nbd.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.nbd.api.dto.ReviewDTO;
import pl.nbd.api.model.Review;
import pl.nbd.api.service.ReviewService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Review addReview(@RequestBody @Valid ReviewDTO reviewDTO) {
        return reviewService.addReview(reviewDTO);
    }

    @GetMapping("/{id}")
    public Review getReview(@PathVariable String id) {
        return reviewService.getReview(id);
    }

    @GetMapping()
    public List<Review> getReviews() {
        return reviewService.getReviews();
    }

    @PutMapping("/{id}")
    public Review updateReview(@PathVariable String id, @RequestBody @Valid ReviewDTO reviewDTO) {
        return reviewService.updateReview(id, reviewDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReview(@PathVariable String id) {
        reviewService.deleteReview(id);
    }
}
