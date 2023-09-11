package com.bookingreviews.controller;

import com.bookingreviews.model.dto.CreateReviewDto;
import com.bookingreviews.model.dto.ReadReviewDto;
import com.bookingreviews.model.dto.ReadReviewsDto;
import com.bookingreviews.model.dto.UpdateReviewDto;
import com.bookingreviews.model.entity.Review;
import com.bookingreviews.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public Review create(@RequestBody CreateReviewDto reviewDto) {
        return reviewService.create(reviewDto);
    }

    @PutMapping("/{id}")
    public Review update(@PathVariable String id, @RequestBody UpdateReviewDto reviewDto) {
        return reviewService.update(id, reviewDto);
    }

    @GetMapping("/{id}")
    public ReadReviewsDto getForReviewee(@PathVariable String id) {
        return reviewService.getAllForReviewee(id);
    }

    @GetMapping("/reviewer/{reviewerId}")
    public List<ReadReviewDto> getAllReviewsForReviewer(@PathVariable String reviewerId) {
        return reviewService.getAllReviewsForReviewer(reviewerId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        reviewService.delete(id);
    }
}
