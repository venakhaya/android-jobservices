package com.usermanager.app.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "User")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler","tasks" })
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Serialized field names.
	private static final String JOIN_TABLE = "User_Task";
	private static final String USER_ID = "user_id";
	private static final String USER_NAME = "user_name";
	private static final String FIRST_NAME = "first_name";
	private static final String LAST_NAME = "last_name";

	@Id
	@GeneratedValue
	@Column(name = USER_ID)
	private long id;
	@JsonProperty(USER_NAME)
	@Column(name = USER_NAME, unique = true)
	private String username;
	@JsonProperty(FIRST_NAME)
	@Column(name = FIRST_NAME)
	private String firstName;
	@JsonProperty(LAST_NAME)
	@Column(name = LAST_NAME)
	private String lastName;
	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = JOIN_TABLE, joinColumns = @JoinColumn(name = USER_ID), inverseJoinColumns = @JoinColumn(name = Task.TASK_ID))
	private List<Task> tasks = new ArrayList<Task>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
