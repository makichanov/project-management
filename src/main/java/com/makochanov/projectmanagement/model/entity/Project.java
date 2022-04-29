package com.makochanov.projectmanagement.model.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "projects_p_id_seq")
    @SequenceGenerator(name = "projects_p_id_seq", sequenceName = "projects_p_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "p_name")
    private String name;

    @Column(name = "p_description")
    private String description;

    @Column(name = "p_create_date")
    private Timestamp createDate;

    @Column(name = "p_is_deleted")
    private Boolean isDeleted;

    @OneToMany(mappedBy = "projectId")
    private List<Task> tasks;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Project project = (Project) o;

        if (id != null ? !id.equals(project.id) : project.id != null) return false;
        if (name != null ? !name.equals(project.name) : project.name != null) return false;
        if (description != null ? !description.equals(project.description) : project.description != null) return false;
        if (createDate != null ? !createDate.equals(project.createDate) : project.createDate != null) return false;
        if (isDeleted != null ? !isDeleted.equals(project.isDeleted) : project.isDeleted != null) return false;
        return tasks != null ? tasks.equals(project.tasks) : project.tasks == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (isDeleted != null ? isDeleted.hashCode() : 0);
        result = 31 * result + (tasks != null ? tasks.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Project{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", createDate=").append(createDate);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", tasks=").append(tasks);
        sb.append('}');
        return sb.toString();
    }
}
