package com.makochanov.projectmanagement.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tasks_t_id_seq")
    @SequenceGenerator(name = "tasks_t_id_seq", sequenceName = "tasks_t_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "t_name")
    private String name;

    @Column(name = "t_description")
    private String description;

    @Column(name = "t_is_completed")
    private Boolean isCompleted;

    @Column(name = "t_create_date")
    private Timestamp createDate;

    @Column(name = "t_completed_date")
    private Timestamp completionDate;

    @Column(name = "t_is_deleted")
    private Boolean isDeleted;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "t_project_id", nullable = false)
    private Project projectId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (id != null ? !id.equals(task.id) : task.id != null) return false;
        if (name != null ? !name.equals(task.name) : task.name != null) return false;
        if (description != null ? !description.equals(task.description) : task.description != null) return false;
        if (isCompleted != null ? !isCompleted.equals(task.isCompleted) : task.isCompleted != null) return false;
        if (createDate != null ? !createDate.equals(task.createDate) : task.createDate != null) return false;
        if (completionDate != null ? !completionDate.equals(task.completionDate) : task.completionDate != null)
            return false;
        if (isDeleted != null ? !isDeleted.equals(task.isDeleted) : task.isDeleted != null) return false;
        return projectId != null ? projectId.equals(task.projectId) : task.projectId == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (isCompleted != null ? isCompleted.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (completionDate != null ? completionDate.hashCode() : 0);
        result = 31 * result + (isDeleted != null ? isDeleted.hashCode() : 0);
        result = 31 * result + (projectId != null ? projectId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Task{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", isCompleted=").append(isCompleted);
        sb.append(", createDate=").append(createDate);
        sb.append(", completionDate=").append(completionDate);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", projectId=").append(projectId);
        sb.append('}');
        return sb.toString();
    }
}
