package com.subhankar.userservice.integration.model;

import lombok.*;

import java.util.List;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HotelDO {
    private String id;
    private String name;
    private String about;
    private List<RatingDO> ratingDOList;
}
