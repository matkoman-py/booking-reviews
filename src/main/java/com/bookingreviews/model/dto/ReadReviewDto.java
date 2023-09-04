package com.bookingreviews.model.dto;


import com.bookingreviews.model.enums.RevieweeType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ReadReviewDto {

    private String id;
    private String reviewerId;
    private String revieweeId;
    private int rating;
    private RevieweeType revieweeType;
}
