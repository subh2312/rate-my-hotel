package com.subhankar.hotelservice.repository;

import com.subhankar.hotelservice.model.DO.HotelDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<HotelDO, String> {
}
