package com.bookingreviews.mapper;

import com.bookingreviews.model.dto.CreateReviewDto;
import com.bookingreviews.model.dto.ReadReviewDto;
import com.bookingreviews.model.dto.UpdateReviewDto;
import com.bookingreviews.model.entity.Review;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    ReviewMapper INSTANCE = Mappers.getMapper( ReviewMapper.class );
    ReadReviewDto toDto(Review review);

    Review toEntity(CreateReviewDto reviewDto);
    void updateEntityFromDto(UpdateReviewDto reviewDto, @MappingTarget Review review);

}
