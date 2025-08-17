package com.propydis.studio.service.mongodb;

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

    public Project update(Project project) {
        if (!projectRepository.existsById(project.getId())) {
            throw new RuntimeException("Project not found");
        }
        return projectRepository.save(project);
    }

    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    public Project findById(String id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
    }

    public void deleteById(String id) {
        if(!projectRepository.existsById(id)) {
            throw new RuntimeException("Project not found");
        }
        projectRepository.deleteById(id);
    }
}
