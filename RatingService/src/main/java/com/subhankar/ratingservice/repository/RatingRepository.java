package com.subhankar.ratingservice.repository;

import com.subhankar.ratingservice.model.DO.RatingDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<RatingDO, String> {
    List<RatingDO> findAllByUserId(String userId);
    List<RatingDO> findAllByHotelId(String hotelId);

    List<RatingDO> findAllByUserIdAndHotelId(String userId, String hotelId);

    void deleteByUserIdAndHotelId(String userId, String hotelId);

}
