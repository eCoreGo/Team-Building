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
    private Double totalFoundation;
    private Double totalUserBalance;
    private Date creationTime;
    private List<TeamMember> users;

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

    public List<TeamMember> getUsers() {
        return users;
    }

    public void setUsers(List<TeamMember> users) {
        this.users = users;
    }
}