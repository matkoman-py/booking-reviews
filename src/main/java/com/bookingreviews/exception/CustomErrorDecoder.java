package com.bookingreviews.exception;

import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        System.out.println("DSADSA");
        System.out.println(response.status());
        return switch (response.status()) {
            case 404 -> new NotFoundException("Entity Not Found");
            case 500 -> new RuntimeException("Internal Server Error");
            default -> new Exception("Unclassified exception");
        };
    }
}