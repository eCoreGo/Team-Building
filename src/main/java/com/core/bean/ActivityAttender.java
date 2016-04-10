package com.core.bean;

/**
 * Created by stereomatrix on 2016/3/20.
 */

public class ActivityAttender {

    private Integer activityId;
    private String userId;
    private String userName;
    private Integer seatsleave;
    private Boolean attended;
    private Integer seatsNo;

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getSeatsleave() {
        return seatsleave;
    }

    public void setSeatsleave(Integer seatsleave) {
        this.seatsleave = seatsleave;
    }

    public Boolean getAttended() {
        return attended;
    }

    public void setAttended(Boolean attended) {
        this.attended = attended;
    }

    public Integer getSeatsNo() {
        return seatsNo;
    }

    public void setSeatsNo(Integer seatsNo) {
        this.seatsNo = seatsNo;
    }
}