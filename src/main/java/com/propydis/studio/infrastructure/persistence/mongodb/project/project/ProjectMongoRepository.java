package com.propydis.studio.infrastructure.persistence.mongodb.project.project;

import com.propydis.studio.domain.project.project.Project;
import com.propydis.studio.domain.project.project.repository.ProjectRepository;
import com.propydis.studio.domain.project.repository.PhotoRepository;
import com.propydis.studio.shared.exception.exceptions.NotFoundByIdException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ProjectMongoRepository implements ProjectRepository {


    private final ProjectDocumentRepository projectDocumentRepository;
    private final PhotoRepository photoRepository;


    public ProjectMongoRepository(ProjectDocumentRepository projectDocumentRepository,
                                  PhotoRepository photoRepository) {
        this.projectDocumentRepository = projectDocumentRepository;
        this.photoRepository = photoRepository;

    }


    @Override
    public Project save(Project project) {
        ProjectDocument projectDocument = ProjectDocumentMapper.toDocument(project);
        ProjectDocument savedDocument = projectDocumentRepository.save(projectDocument);
        return ProjectDocumentMapper.toDomain(savedDocument);
    }

    @Override
    public List<Project> findAll() {
        return projectDocumentRepository.findAll().stream().map(ProjectDocumentMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public Optional<Project> findById(String id) {
        return projectDocumentRepository.findById(id).map(ProjectDocumentMapper::toDomain);
    }


    @Override
    public Project update(Project project) {
        ProjectDocument existing = projectDocumentRepository.findById(project.getId())
                .orElseThrow(() -> new NotFoundByIdException(project.getId(), "project"));

        existing.setName(project.getName());
        existing.setDescription(project.getDescription());
        existing.setPhotoIds(project.getPhotoIds());
        existing.setUpdatedAt(project.getUpdatedAt());
        existing.setProjectStatus(project.getProjectStatus());

        ProjectDocument updatedDocument = projectDocumentRepository.save(existing);
        return ProjectDocumentMapper.toDomain(updatedDocument);
    }



    @Override
    public void deleteById(String id) {
        ProjectDocument existing = projectDocumentRepository.findById(id)
                .orElseThrow(() -> new NotFoundByIdException(id, "project"));

        photoRepository.deleteAllById(existing.getPhotoIds());

        projectDocumentRepository.deleteById(id);
    }


}




