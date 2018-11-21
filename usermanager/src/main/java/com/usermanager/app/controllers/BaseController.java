package com.usermanager.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import com.usermanager.app.repository.TaskRepository;
import com.usermanager.app.repository.UserRepository;
import com.usermanager.app.repository.UserTaskRepositoryQueryManager;
import com.usermanager.app.repository.UserTaskRepository;

public class BaseController {
	@Autowired
	public UserRepository userRepository;
	@Autowired
	public TaskRepository taskRepository;
	@Autowired
	public UserTaskRepository userTaskRepository;
	@Autowired
	public UserTaskRepositoryQueryManager userTaskRepositoryQueryManager;
}
