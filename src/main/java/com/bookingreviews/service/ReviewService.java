package com.bookingreviews.service;


import com.bookingreviews.exception.InvalidRoleException;
import com.bookingreviews.exception.NotFoundException;
import com.bookingreviews.exception.OutOfBoundsException;
import com.bookingreviews.mapper.ReviewMapper;
import com.bookingreviews.messaging.KafkaProducer;
import com.bookingreviews.model.dto.*;
import com.bookingreviews.model.entity.Review;
import com.bookingreviews.model.enums.NotificationType;
import com.bookingreviews.model.enums.RevieweeType;
import com.bookingreviews.model.enums.UserRole;
import com.bookingreviews.proxy.BookingAccomodationProxy;
import com.bookingreviews.proxy.UserProxy;
import com.bookingreviews.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserProxy userProxy;
    private final BookingAccomodationProxy bookingAccomodationProxy;
    private final KafkaProducer kafkaProducer;


    public Review create(CreateReviewDto reviewDto) {
        if(reviewDto.getRating() < 0 || reviewDto.getRating() > 5)
            throw new OutOfBoundsException("Rating must be between 1 and 5");

        Review review = reviewRepository.findByReviewerIdAndRevieweeId(reviewDto.getReviewerId(), reviewDto.getRevieweeId()).isPresent() ?
                reviewRepository.findByReviewerIdAndRevieweeId(reviewDto.getReviewerId(), reviewDto.getRevieweeId()).get() : ReviewMapper.INSTANCE.toEntity(reviewDto);
        UserInfoDto reviewerInfo = userProxy.getUser(reviewDto.getReviewerId());
        if(!reviewerInfo.getRole().equals(UserRole.GUEST))
            throw new InvalidRoleException("User of role Host can't leave a review");
        Boolean canReview = reviewDto.getRevieweeType().equals(RevieweeType.HOST) ?
                bookingAccomodationProxy.canGuestReviewHost(reviewDto.getReviewerId(), reviewDto.getRevieweeId()) :
                bookingAccomodationProxy.canGuestReviewAccomodation(reviewDto.getReviewerId(), reviewDto.getRevieweeId());
        if(!canReview) throw new NotFoundException("No reservations for this host/accomodation was found");
        review.setReviewedOn(LocalDateTime.now());

        if(reviewDto.getRevieweeType().equals(RevieweeType.HOST)){
            kafkaProducer.sendMessage(NotificationDTO
                    .builder()
                    .message(String.format("You have a new profile review from: %s", reviewDto.getReviewerId()))
                    .userId(reviewDto.getRevieweeId())
                    .notificationType(NotificationType.NEW_PROFILE_REVIEW)
                    .build());
        }
        else{
            AccomodationDTO accomodationDTO = bookingAccomodationProxy.getOneAccommodation(reviewDto.getRevieweeId());
            kafkaProducer.sendMessage(NotificationDTO
                    .builder()
                    .message(String.format("You have a new accommodation review from: %s", reviewDto.getReviewerId()))
                    .userId(accomodationDTO.getHostId())
                    .notificationType(NotificationType.NEW_ACCOMODATION_REVIEW)
                    .build());
        }


        return reviewRepository.save(review);
    }

    public Review update(String id, UpdateReviewDto reviewDto) {
        if(reviewDto.getRating() < 0 || reviewDto.getRating() > 5)
            throw new OutOfBoundsException("Rating must be between 1 and 5");
        Review review = reviewRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Entity with id %s does not exist", id)));
        ReviewMapper.INSTANCE.updateEntityFromDto(reviewDto, review);
        review.setReviewedOn(LocalDateTime.now());
        return reviewRepository.save(review);
    }



    public ReadReviewsDto getAllForReviewee(String revieweeid) {
        List<ReadReviewDto> reviews = reviewRepository.findByRevieweeId(revieweeid).stream()
                .map(ReviewMapper.INSTANCE::toDto).toList();
        double averageRating = reviews.stream()
                .mapToInt(ReadReviewDto::getRating)
                .average()
                .orElse(0.0);
        return ReadReviewsDto.builder()
                .reviews(reviews)
                .averageRating(averageRating)
                .build();
    }

    public void deleteAllForReviewee(String revieweeid) {
        List<Review> reviews = reviewRepository.findByRevieweeId(revieweeid);
        reviewRepository.deleteAll(reviews);
    }

    public void deleteAllForReviewer(String reviewerid) {
        List<Review> reviews = reviewRepository.findByRevieweeId(reviewerid);
        reviewRepository.deleteAll(reviews);
    }

    public void delete(String id) {
        reviewRepository.deleteById(id);
    }

    public List<ReadReviewDto> getAllReviewsForReviewer(String reviewerId) {
        return reviewRepository.findByReviewerId(reviewerId).stream()
                .map(ReviewMapper.INSTANCE::toDto).toList();
    }
}
