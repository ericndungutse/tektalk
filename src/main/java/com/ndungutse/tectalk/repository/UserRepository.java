package com.ndungutse.tectalk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ndungutse.tectalk.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}