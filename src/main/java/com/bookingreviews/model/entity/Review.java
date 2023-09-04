package com.bookingreviews.model.entity;


import com.bookingreviews.model.enums.RevieweeType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String reviewerId;
    private String revieweeId;
    private LocalDateTime reviewedOn;

    @Min(1)
    @Max(5)
    private int rating;
    @Enumerated(EnumType.STRING)
    private RevieweeType revieweeType;
}
