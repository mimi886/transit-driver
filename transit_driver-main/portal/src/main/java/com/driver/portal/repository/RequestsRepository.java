package com.driver.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.driver.portal.models.Requests;

public interface RequestsRepository extends JpaRepository<Requests,Long> {

} 