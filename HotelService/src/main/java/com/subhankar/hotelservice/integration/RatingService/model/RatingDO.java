package com.subhankar.hotelservice.integration.RatingService.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RatingDO {
    private String id;
    private String userId;
    private String hotelId;
    private int rating;
    private String comment;
}
