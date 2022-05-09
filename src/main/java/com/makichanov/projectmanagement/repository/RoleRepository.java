package com.makichanov.projectmanagement.repository;

import com.makichanov.projectmanagement.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface Role repository.
 */
public interface RoleRepository extends JpaRepository<Role, Integer> {

    /**
     * Find by name role.
     *
     * @param name the name
     * @return the role
     */
    Role findByName(String name);

}
