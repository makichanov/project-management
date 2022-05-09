package com.makichanov.projectmanagement.repository;

import com.makichanov.projectmanagement.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * The interface User repository.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Find by username user.
     *
     * @param username the username
     * @return the user
     */
    User findByUsername(String username);

    /**
     * Sets deleted status.
     *
     * @param userId    the user id
     * @param isDeleted the is deleted
     * @return the deleted status
     */
    @Modifying
    @Query("update User u set u.isDeleted = ?2 where u.id = ?1")
    int setDeletedStatus(Long userId, Boolean isDeleted);

}
