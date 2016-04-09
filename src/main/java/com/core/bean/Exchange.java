package com.core.bean;

import com.core.json.CustomDateSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

/**
 * Created by stereomatrix on 2016/4/9.
 */
public class Exchange {

    public static final Integer TEAM_FOUNDATION = 1;        //团队经费
    public static final Integer BAD_DEBT = 2;               //坏账
    public static final Integer RECHARGE = 3;               //充值
    public static final Integer TAXI_FEE = 4;               //出租车费
    public static final Integer DRAWBACK = 5;               //退款
    public static final Integer SHARE_EQUALLY = 6;          //均摊
    public static final Integer ACTIVITY_TOTAL_COST = 7;    //活动总开销

    private Integer id;
    private Member member;
    private Activity activity;
    private Team team;
    private Double value;
    private Date date;
    private Integer type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
