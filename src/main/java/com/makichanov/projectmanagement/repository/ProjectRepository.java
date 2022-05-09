package com.makichanov.projectmanagement.repository;

import com.makichanov.projectmanagement.model.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * The interface Project repository for Project entity.
 */
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>, JpaSpecificationExecutor<Project> {

    /**
     * Sets closed status.
     *
     * @param projectId the project id
     * @param isClosed  the is closed
     * @return the closed status
     */
    @Modifying
    @Query("update Project p set p.isClosed = ?2 where p.id = ?1")
    int setClosedStatus(Long projectId, Boolean isClosed);

}
