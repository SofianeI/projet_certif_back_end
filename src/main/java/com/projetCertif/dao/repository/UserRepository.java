package com.projetCertif.dao.repository;


import com.projetCertif.dao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByFirstname(String firstname);
    List<User> findAllByFirstname(String firstname);
    List<User> findAllByLastname(String lastname);
    List<User> findAllByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.username = :username AND u.password = :password ")
    User findbyUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}
