package org.subhankar.addressservice.repository;

import org.springframework.data.jpa.repository.Query;
import org.subhankar.addressservice.model.DO.AddressDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<AddressDO, String> {
    List<AddressDO> findAllByConsumer(String id);

    @Query("SELECT a FROM AddressDO a WHERE a.consumer = ?1 AND a.addressType = ?2")
    List<AddressDO> findAllByConsumerAndAddressTypeEquals(String id, String addressType);
}
