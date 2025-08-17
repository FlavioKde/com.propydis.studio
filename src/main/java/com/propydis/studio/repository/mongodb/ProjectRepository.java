package com.propydis.studio.repository.mongodb;

import com.propydis.studio.model.mongodb.Project;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProjectRepository extends MongoRepository<Project, String> {
}
