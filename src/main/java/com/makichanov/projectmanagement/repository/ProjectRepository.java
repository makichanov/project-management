package com.makichanov.projectmanagement.repository;

import com.makichanov.projectmanagement.model.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>, JpaSpecificationExecutor<Project> {

    @Modifying
    @Query("update Project p set p.isClosed = ?2 where p.id = ?1")
    int setClosedStatus(Long projectId, Boolean isClosed);

}
