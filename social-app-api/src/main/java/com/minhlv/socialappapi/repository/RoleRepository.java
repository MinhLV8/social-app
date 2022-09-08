package com.minhlv.socialappapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.minhlv.socialappapi.common.ERole;
import com.minhlv.socialappapi.entity.SystemRoleEntity;

@Repository
public interface RoleRepository extends JpaRepository<SystemRoleEntity, Long> {

    Optional<SystemRoleEntity> findByRole(ERole role);

    SystemRoleEntity findByRole(String role);
}
