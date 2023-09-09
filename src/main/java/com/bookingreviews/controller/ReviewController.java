package com.bookingreviews.controller;

import com.bookingreviews.model.dto.CreateReviewDto;
import com.bookingreviews.model.dto.ReadReviewsDto;
import com.bookingreviews.model.entity.Review;
import com.bookingreviews.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public Review create(@RequestBody CreateReviewDto reviewDto) {
        return reviewService.create(reviewDto);
    }

    @GetMapping("/{id}")
    public ReadReviewsDto getForReviewee(@PathVariable String id) {
        return reviewService.getAllForReviewee(id);
    }
}
