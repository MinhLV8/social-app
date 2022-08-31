package com.minhlv.socialappapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.minhlv.socialappapi.entity.AccountEntity;

@Repository
public interface AccountRepository extends CrudRepository<AccountEntity, Long> {

}
