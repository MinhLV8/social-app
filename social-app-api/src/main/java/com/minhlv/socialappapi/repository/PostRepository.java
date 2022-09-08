package com.minhlv.socialappapi.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.minhlv.socialappapi.entity.AccountEntity;
import com.minhlv.socialappapi.entity.PostEntity;

@Repository
public interface PostRepository extends CrudRepository<PostEntity, Long> {

    List<PostEntity> findAllByAccount(AccountEntity id);

}
