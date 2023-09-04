package com.bookingreviews;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BookingReviewsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookingReviewsApplication.class, args);
	}

}
