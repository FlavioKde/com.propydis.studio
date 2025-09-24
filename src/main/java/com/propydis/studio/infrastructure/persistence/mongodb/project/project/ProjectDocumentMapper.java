package com.propydis.studio.infrastructure.persistence.mongodb.project.project;

import com.propydis.studio.domain.project.project.Project;

public class ProjectDocumentMapper {

        public static Project toDomain(ProjectDocument projectDocument) {
            if (projectDocument == null) {
                return null;
            }

            Project project = new Project();
            project.setId(projectDocument.getId());
            project.setName(projectDocument.getName());
            project.setPhotoIds(projectDocument.getPhotoIds());
            project.setDescription(projectDocument.getDescription());
            project.setCreatedAt(projectDocument.getCreatedAt());
            project.setUpdatedAt(projectDocument.getUpdatedAt());
            project.setProjectStatus(projectDocument.getProjectStatus());

            return project;

        }

        public static ProjectDocument toDocument(Project project) {
            if (project == null) {
                return null;
            }
            ProjectDocument projectDocument = new ProjectDocument();
            projectDocument.setId(project.getId());
            projectDocument.setName(project.getName());
            projectDocument.setPhotoIds(project.getPhotoIds());
            projectDocument.setDescription(project.getDescription());
            projectDocument.setCreatedAt(project.getCreatedAt());
            projectDocument.setUpdatedAt(project.getUpdatedAt());
            projectDocument.setProjectStatus(project.getProjectStatus());

            return projectDocument;
        }
}
