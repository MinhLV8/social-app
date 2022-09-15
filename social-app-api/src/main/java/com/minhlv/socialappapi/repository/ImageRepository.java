package com.minhlv.socialappapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.minhlv.socialappapi.entity.ImageEntity;

@Repository
public interface ImageRepository extends CrudRepository<ImageEntity, Long> {
    ImageEntity findByFileName(String fileName);
}
