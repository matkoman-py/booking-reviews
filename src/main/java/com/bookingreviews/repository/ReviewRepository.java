package com.bookingreviews.repository;

import com.bookingreviews.model.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, String> {
    List<Review> findByRevieweeId(String revieweeId);
    List<Review> findByReviewerId(String reviewerId);

}
