package com.minhlv.socialappapi.repository;

import com.minhlv.socialappapi.entity.SystemUserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends CrudRepository<SystemUserEntity, Long> {

	@Query(value = "SELECT SU.* FROM SYSTEM_USER SU INNER JOIN SYSTEM_ACCOUNT SA ON SU.ID = SA.USER_ID WHERE SU.USERNAME =:username AND SA.IS_DELETE = 0", nativeQuery = true)
	SystemUserEntity findByUsername(@Param("username") String username);

	SystemUserEntity findById(long id);

	boolean existsByUsername(String username);

	@Transactional
	void deleteByUsername(String username);
}
