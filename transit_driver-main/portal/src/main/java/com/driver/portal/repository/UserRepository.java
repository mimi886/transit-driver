package com.driver.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.driver.portal.models.User;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User,Integer>{
    Optional<User> findByEmail(String email);
}
 