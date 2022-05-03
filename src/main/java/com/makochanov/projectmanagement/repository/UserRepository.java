package com.makochanov.projectmanagement.repository;

import com.makochanov.projectmanagement.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    @Modifying
    @Query("update User u set u.isDeleted = ?2 where u.id = ?1")
    int setDeletedStatus(Long userId, Boolean isDeleted);

}
