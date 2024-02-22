package com.subhankar.userservice.repository;

import com.subhankar.userservice.model.DO.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserDO, String> {


}
