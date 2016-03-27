package com.core.bean;

import java.util.Date;
import java.util.List;

/**
 * Created by stereomatrix on 2016/3/20.
 */
public class Team {
    private Integer id;
    private Boolean temp;
    private String name;
    private String description;
    private Double totalFoundation;
    private Double totalUserBalance;
    private Date creationTime;
    private List<TeamMember> teamMembers;
    private List<Member> members;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getTemp() {
        return temp;
    }

    public void setTemp(Boolean temp) {
        this.temp = temp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getTotalFoundation() {
        return totalFoundation;
    }

    public void setTotalFoundation(Double totalFoundation) {
        this.totalFoundation = totalFoundation;
    }

    public Double getTotalUserBalance() {
        return totalUserBalance;
    }

    public void setTotalUserBalance(Double totalUserBalance) {
        this.totalUserBalance = totalUserBalance;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public List<TeamMember> getTeamMembers() {
        return teamMembers;
    }

    public void setTeamMembers(List<TeamMember> teamMembers) {
        this.teamMembers = teamMembers;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }
}
