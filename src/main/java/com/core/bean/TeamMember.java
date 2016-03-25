package com.core.bean;

import java.util.Date;

/**
 * Created by stereomatrix on 2016/3/20.
 */
public class TeamMember {
    private Member member;
    private Team team;
    private Double balance;
    private Date attendTime;

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Date getAttendTime() {
        return attendTime;
    }

    public void setAttendTime(Date attendTime) {
        this.attendTime = attendTime;
    }
}
