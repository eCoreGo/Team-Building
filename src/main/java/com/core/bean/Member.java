package com.core.bean;

import java.util.List;

public class Member {
	private String id;
	private String name;
	private String phone;
	private List<TeamMember> teams;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<TeamMember> getTeams() {
		return teams;
	}

	public void setTeams(List<TeamMember> teams) {
		this.teams = teams;
	}
}
