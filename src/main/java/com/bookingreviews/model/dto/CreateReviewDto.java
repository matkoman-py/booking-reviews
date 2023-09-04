package com.bookingreviews.model.dto;


import com.bookingreviews.model.enums.RevieweeType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreateReviewDto {
    private String reviewerId;
    private String revieweeId;
    private int rating;
    @Enumerated(EnumType.STRING)
    private RevieweeType revieweeType;
}
