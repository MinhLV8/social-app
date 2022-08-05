package com.minhlv.socialappapi.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.minhlv.socialappapi.entity.SystemUser;

@Repository
public interface UserRepository extends CrudRepository<SystemUser, Long> {

    @Query(value = "SELECT SU.* FROM SYSTEM_USER SU INNER JOIN SYSTEM_ACCOUNT SA ON SU.ID = SA.USERFKID "
            + "WHERE SU.USERNAME =:username AND SA.IS_DELETE = 0", nativeQuery = true)
    SystemUser findByUsername(@Param("username") String username);

    SystemUser findById(long id);
}
