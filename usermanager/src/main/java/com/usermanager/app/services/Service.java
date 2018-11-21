package com.usermanager.app.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.usermanager.app.domain.Task;
import com.usermanager.app.repository.UserTaskRepositoryQueryManager;

@Component
public class Service {
	private static final Logger log = LoggerFactory.getLogger(Service.class);

	@Autowired
	public UserTaskRepositoryQueryManager userTaskRepositoryQueryManager;

	@Scheduled(fixedRate = 10000)
	public void reportCurrentTime() {
		List<Task> list = userTaskRepositoryQueryManager.getAllIncompleteTask();
		for (Task task : list) {
			log.debug("Task Name : %s End date :% State : %s", task.getName(), task.getDateTime(),
					task.getState().toString());
		}
	}

}
