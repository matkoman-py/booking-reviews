package com.bookingreviews.model.dto;

import com.bookingreviews.model.enums.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class NotificationDTO {
    private String userId;
    private String message;
    private NotificationType notificationType;
}
