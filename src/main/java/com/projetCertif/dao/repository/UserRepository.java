package com.projetCertif.dao.repository;


import com.projetCertif.dao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByFirstname(String firstname);
    List<User> findAllByFirstname(String firstname);
}
