package com.usermanager.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.usermanager.app.domain.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
	
}
