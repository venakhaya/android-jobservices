package com.usermanager.app.controllers;


import javax.transaction.Transactional;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.usermanager.app.domain.MessageResponse;
import com.usermanager.app.domain.Task;
import com.usermanager.app.domain.User;
import com.usermanager.app.domain.UserTask;
import com.usermanager.app.utils.Utilility;

@RestController
@RequestMapping("/api/user")
@Transactional
public class TaskController extends BaseController {
	public static final String USER_TASK_PATH = "/{user_id}/task";
	public static final String USER_TASK_PATH_INFO = "/{user_id}/task/{task_id}";

	@RequestMapping(value = USER_TASK_PATH, method = RequestMethod.POST)
	public @ResponseBody Object createTask(@PathVariable(value = "user_id") Long id, @RequestBody Task taskPayload,@RequestHeader HttpHeaders headers) {
		
		if(!Utilility.isValidFormat(taskPayload.getDateTime())) {
			return new MessageResponse("Invalid date format");
		}
		if(taskPayload != null) {
			if(taskPayload.getName() == null || taskPayload.getName().length() == 0) {
				return new MessageResponse("Task name can not be null");
			}
		}
		Task task = taskRepository.save(taskPayload);
		userTaskRepository.save(new UserTask(id, task.getId()));
		return task;
	}

	@RequestMapping(value = USER_TASK_PATH, method = RequestMethod.GET)
	public @ResponseBody Object getAllTasks(@PathVariable(value = "user_id") Long userId,@RequestHeader HttpHeaders headers) {
		User user = userRepository.getOne(userId);
		if(user == null) {
			 return new MessageResponse("User not registered");
		}
		return userTaskRepositoryQueryManager.findTasksById(userId);
	}

	@RequestMapping(value = USER_TASK_PATH_INFO, method = RequestMethod.PUT)
	public @ResponseBody Object updateTask(@PathVariable(value = "user_id") Long userId,
			@PathVariable(value = "task_id") Long taskId, @RequestBody Task taskPayload,@RequestHeader HttpHeaders headers) {
		Task task = null;
		if (taskPayload != null && isValidUserTask(userId, taskId)) {
			taskPayload.setId(taskId);
			task = taskRepository.save(taskPayload);
			return task;
		}
		return new MessageResponse("Ivalid task could not update");
	}

	@RequestMapping(value = USER_TASK_PATH_INFO, method = RequestMethod.GET)
	public @ResponseBody Object getTaskIfo(@PathVariable(value = "user_id") Long userId,
			@PathVariable(value = "task_id") Long taskId,@RequestHeader HttpHeaders headers) {
		if (isValidUserTask(userId, taskId)) {
			return  taskRepository.getOne(taskId);
		}
		return new MessageResponse("Task info not found");
	}

	@RequestMapping(value = USER_TASK_PATH_INFO, method = RequestMethod.DELETE)
	public @ResponseBody MessageResponse deleteTask(@PathVariable(value = "user_id") Long userId,
			@PathVariable(value = "task_id") Long taskId,@RequestHeader HttpHeaders headers) {
		System.out.println("Deleting");
		if (isValidUserTask(userId, taskId) ) {
			userTaskRepositoryQueryManager.deletByUserTask(taskId);
			taskRepository.deleteById(taskId);
		}else {
			return new MessageResponse(String.format("Task #%s was not found",String.valueOf(taskId)));
		}
		return new MessageResponse("Successfull deleted task #"+taskId);
	}

	private boolean isValidUserTask(long userId, long taskid) {
		UserTask userTask = userTaskRepositoryQueryManager.findUserTask(taskid, userId);
		return userTask != null;
	}
}
