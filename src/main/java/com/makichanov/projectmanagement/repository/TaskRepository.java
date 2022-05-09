package com.makichanov.projectmanagement.repository;

import com.makichanov.projectmanagement.model.entity.Project;
import com.makichanov.projectmanagement.model.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Task repository.
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    /**
     * Find by project list.
     *
     * @param project the project
     * @return the list
     */
    List<Task> findByProject(Project project);

    /**
     * Sets completed status.
     *
     * @param taskId   the task id
     * @param isClosed the is closed
     * @return the completed status
     */
    @Modifying
    @Query("update Task t set t.isCompleted = ?2 where t.id = ?1")
    int setCompletedStatus(Long taskId, Boolean isClosed);

    /**
     * Sets deleted status.
     *
     * @param taskId    the task id
     * @param isDeleted the is deleted
     * @return the deleted status
     */
    @Modifying
    @Query("update Task t set t.isDeleted = ?2 where t.id = ?1")
    int setDeletedStatus(Long taskId, Boolean isDeleted);

}
