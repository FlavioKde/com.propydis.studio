package com.propydis.studio.domain.project.project.repository;

import com.propydis.studio.domain.project.project.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository {

            Project save(Project project);
            List<Project> findAll();
            Optional<Project> findById(String id);
            void deleteById(String id);


}
