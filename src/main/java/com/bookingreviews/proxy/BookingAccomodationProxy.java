package com.bookingreviews.proxy;

import com.bookingreviews.model.dto.AccomodationDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "accommodation-service")
public interface BookingAccomodationProxy {
    @GetMapping("api/accommodation/booking/previous-reservations-exist/host/{userId}/{hostId}")
    Boolean canGuestReviewHost(@PathVariable String userId, @PathVariable String hostId);

    @GetMapping("api/accommodation/booking/previous-reservations-exist/accomodation/{userId}/{id}")
    Boolean canGuestReviewAccomodation(@PathVariable String userId, @PathVariable String id);

    @GetMapping("api/accommodation/{id}")
    AccomodationDTO getOneAccommodation(@PathVariable String id);
}
