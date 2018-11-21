package com.usermanager.app.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.usermanager.app.domain.Task;
import com.usermanager.app.domain.UserTask;

@Repository
public class UserTaskRepositoryQueryManager {

	private static final String GET_TASKS_BY_ID_QUERY = "SELECT USER_TASK_ID,NAME,DESCRIPTION FROM USER_TASK INNER JOIN TASK ON USER_TASK.USER_ID = ";
	private static final String GET_INCOMPLETE_TASKS_QUERY = "SELECT USER_TASK_ID,NAME,DESCRIPTION,DATE_TIME,STATE FROM USER_TASK INNER JOIN TASK ON USER_TASK.TASK_ID = TASK.TASK_ID where TASK.STATE != 'DONE'";
	private static final String DELETE_TASK_BY_TASK_ID_COMMAND = "DELETE FROM USER_TASK where USER_TASK.TASK_ID = ";
	private static final String DELETE_TASK_BY_USER_ID_COMMAND = "DELETE   FROM USER_TASK USER_TASK where USER_TASK.USER_ID = ";
	private static final String GET_USER_TASK_QUERY = "SELECT * FROM USER_TASK where USER_TASK.TASK_ID = %s AND USER_TASK.USER_ID = %s";

	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<Task> findTasksById(long id) {

		List<Map<String, Object>> map = jdbcTemplate.queryForList(GET_TASKS_BY_ID_QUERY + id);
		List<Task> list = new ArrayList<Task>();
		for (Map<String, Object> row : map) {
			Task task = new Task();
			task.setId((Long) (row.get("USER_TASK_ID")));
			task.setName((String) row.get("NAME"));
			task.setDiscreption((String) row.get("DESCRIPTION"));
			task.setDateTime((String) row.get("DATE_TIME"));
			task.setState( (String)row.get("STATE"));
			list.add(task);
		}
		return list;
	}

	public List<Task> getAllIncompleteTask() {

		List<Map<String, Object>> map = jdbcTemplate.queryForList(GET_INCOMPLETE_TASKS_QUERY);
		List<Task> list = new ArrayList<Task>();
		for (Map<String, Object> row : map) {
			Task task = new Task();
			task.setId((Long) (row.get("USER_TASK_ID")));
			task.setName((String) row.get("NAME"));
			task.setDiscreption((String) row.get("DESCRIPTION"));
			task.setDateTime((String) row.get("DATE_TIME"));
			task.setState((String) row.get("STATE"));
			list.add(task);
		}
		return list;
	}

	public UserTask findUserTask(long taskId, long userId) {
		List<Map<String, Object>> map = jdbcTemplate
				.queryForList(String.format(GET_USER_TASK_QUERY, taskId + "", userId + ""));
		UserTask userTask = null;
		for (Map<String, Object> row : map) {
			userTask = new UserTask((Long) row.get("USER_ID"), (Long) row.get("TASK_ID"));
			userTask.setId((Long) (row.get("USER_TASK_ID")));
		}
		return userTask;
	}

	public boolean deletByUserId(long userId) {

		int i = jdbcTemplate.update(DELETE_TASK_BY_USER_ID_COMMAND + userId);

		return false;
	}

	public boolean deletByUserTask(long taskId) {

		int i = jdbcTemplate.update(DELETE_TASK_BY_TASK_ID_COMMAND + taskId);

		return false;
	}

}
