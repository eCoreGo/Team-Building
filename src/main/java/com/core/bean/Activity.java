package com.core.bean;

import java.util.Date;

/**
 * Created by stereomatrix on 2016/3/20.
 */
public class Activity {
    public static enum Status {
        TODO(1),
        DOING(2),
        DONE(3);

        private int value;
        Status(int value) {
            this.value = value;
        }
        public int getValue() {
            return value;
        }
        public static Status getByValue(int value) {
            for(Status status : Status.values()) {
                if(value == status.getValue()) {
                    return status;
                }
            }
            return null;
        }
    }
    private Integer id;
    private String name;
    private Double totalCost;
    private Double totalFoundationCost;
    private String description;
    private Status status;
    private Date startTime;
    private Date endTime;
    private Integer teamId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public Double getTotalFoundationCost() {
        return totalFoundationCost;
    }

    public void setTotalFoundationCost(Double totalFoundationCost) {
        this.totalFoundationCost = totalFoundationCost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }
}


