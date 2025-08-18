package com.propydis.studio.service.mongodb;

import com.propydis.studio.exception.exceptions.NotFoundByIdException;
import com.propydis.studio.model.mongodb.Project;
import com.propydis.studio.repository.mongodb.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project save(Project project) {
        return projectRepository.save(project);
    }

    public Project update(Project project, String id) {
        Project existing = projectRepository.findById(id)
                .orElseThrow(() -> new NotFoundByIdException(id, "project"));

        existing.setName(project.getName());
        existing.setDescription(project.getDescription());
        existing.setPhotos(project.getPhotos());


        return projectRepository.save(project);


    }

    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    public Project findById(String id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new NotFoundByIdException(id, "project"));
    }

    public void deleteById(String id) {
        Project existing = projectRepository.findById(id)
                .orElseThrow(() -> new NotFoundByIdException(id, "project"));

        projectRepository.delete(existing);
    }
}