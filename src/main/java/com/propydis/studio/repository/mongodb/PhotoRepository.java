package com.propydis.studio.repository.mongodb;

import com.propydis.studio.model.mongodb.Photo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PhotoRepository extends MongoRepository<Photo, String> {
}
