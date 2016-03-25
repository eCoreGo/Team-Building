package com.core.bean;

/**
 * Created by stereomatrix on 2016/3/20.
 */
public class ActivityAttender {
    private Integer activityId;
    private Integer userId;
    private Integer seats;

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }
}
