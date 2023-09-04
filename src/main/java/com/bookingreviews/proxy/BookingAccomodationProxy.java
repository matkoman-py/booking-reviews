package com.bookingreviews.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "accommodation-service")
public interface BookingAccomodationProxy {
    @GetMapping("api/booking/previous-reservations-exist/host/{userId}/{hostId}")
    Boolean canGuestReviewHost(@PathVariable String userId, @PathVariable String hostId);

    @GetMapping("api/booking/previous-reservations-exist/accomodation/{userId}/{id}")
    Boolean canGuestReviewAccomodation(@PathVariable String userId, @PathVariable String id);

}
