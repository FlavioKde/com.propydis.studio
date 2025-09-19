package com.propydis.studio.repository.mongodb;

import com.propydis.studio.domain.project.Project;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProjectRepository extends MongoRepository<Project, String> {
}
