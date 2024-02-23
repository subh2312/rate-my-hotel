package org.subhankar.addressservice.repository;

import org.subhankar.addressservice.model.DO.AddressDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<AddressDO, String> {
}
