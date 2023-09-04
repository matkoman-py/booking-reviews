package com.bookingreviews.proxy;

import com.bookingreviews.model.dto.UserInfoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "users-service")
public interface UserProxy {
    @GetMapping("api/users/{id}")
    UserInfoDto getUser(@PathVariable String id);
}