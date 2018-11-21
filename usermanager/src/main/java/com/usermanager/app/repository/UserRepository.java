package com.usermanager.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usermanager.app.domain.User;


public interface UserRepository extends JpaRepository<User, Long> {

}
