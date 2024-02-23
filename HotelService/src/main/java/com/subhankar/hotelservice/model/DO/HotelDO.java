package com.subhankar.hotelservice.model.DO;

import com.subhankar.hotelservice.integration.RatingService.model.RatingDO;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "hotels")
public class HotelDO {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String about;
    @Transient
    private List<RatingDO> ratings = new ArrayList<>();
}
