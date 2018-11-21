package com.usermanager.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usermanager.app.domain.Task;
import com.usermanager.app.domain.UserTask;


public interface UserTaskRepository extends JpaRepository<UserTask, Long> {

}
