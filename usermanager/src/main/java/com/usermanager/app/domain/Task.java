package com.usermanager.app.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "Task")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Task implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String TASK_ID = "task_id";
	private static final String NAME = "name";
	private static final String DISCREPTION = "description";
	private static final String DATE_TIME = "date_time";
	private static final String STATE = "state";

	@Id
	@GeneratedValue
	@Column(name = TASK_ID)
	private long id;
	@JsonProperty(NAME)
	@Column(name = NAME)
	private String name;
	@JsonProperty(DISCREPTION)
	@Column(name = DISCREPTION)
	private String discreption;
	@JsonProperty(DATE_TIME)
	@Column(name = DATE_TIME)
	private String dateTime;
	@Enumerated(EnumType.STRING)
	@JsonProperty(STATE)
	private State state;
	@ManyToMany(mappedBy = "tasks")
	private List<User> users = new ArrayList<User>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDiscreption() {
		return discreption;
	}

	public void setDiscreption(String discreption) {
		this.discreption = discreption;
	}

	public void addUser(User user) {

		users.add(user);
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public static String getState() {
		return STATE;
	}

	public void setState(String state) {
		if (State.NONE.toString().equals(state)) {
			this.state = State.NONE;
		} else if (State.INPROGRESS.toString().equals(state)) {
			this.state = State.INPROGRESS;
		} else if (State.DONE.toString().equals(state)) {
			this.state = State.DONE;
		}
	}
}
