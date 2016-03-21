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
    }
    private Integer id;
    private String name;
    private Double totalCost;
    private Double totalFoundationCost;
    private Date time;
    private String description;
    private Status status;

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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
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
}


