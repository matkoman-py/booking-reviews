package com.bookingreviews.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AccomodationDTO {
    private String id;
    private String name;
    private String location;
    private boolean automaticApprove;
    private List<String> photographs;
    private int minGuests;
    private int maxGuests;
    private String hostId;
}
