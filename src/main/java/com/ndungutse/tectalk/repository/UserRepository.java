package com.ndungutse.tectalk.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ndungutse.tectalk.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}