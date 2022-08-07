package com.minhlv.socialappapi.repository;

import com.minhlv.socialappapi.common.ERole;
import com.minhlv.socialappapi.entity.SystemRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<SystemRoleEntity, Long> {
	Optional<SystemRoleEntity> findByRole(ERole role);
}
