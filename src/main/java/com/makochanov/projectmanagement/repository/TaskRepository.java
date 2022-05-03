package com.makochanov.projectmanagement.repository;

import com.makochanov.projectmanagement.model.entity.Project;
import com.makochanov.projectmanagement.model.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByProject(Project project);

    @Modifying
    @Query("update Task t set t.isClosed = ?2 where t.id = ?1")
    int setClosedStatus(Long taskId, Boolean isClosed);

    @Modifying
    @Query("update Task t set t.isDeleted = ?2 where t.id = ?1")
    int setDeletedStatus(Long taskId, Boolean isDeleted);

}
