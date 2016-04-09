package com.core.bean;

import java.util.Date;

/**
 * Created by wanglan on 16/3/29.
 */
public class ExchangeDetail {

    public static final Integer TEAM_FOUNDATION = 1;        //团队经费
    public static final Integer BAD_DEBT = 2;               //坏账
    public static final Integer RECHARGE = 3;               //充值
    public static final Integer TAXI_FEE = 4;               //出租车费
    public static final Integer DRAWBACK = 5;               //退款
    public static final Integer SHARE_EQUALLY = 6;          //均摊
    public static final Integer ACTIVITY_TOTAL_COST = 7;    //活动总开销

    public static enum ExchangeStatus {
        ADD(1),
        DELETE(2),
        READD(3);

        private int value;
        ExchangeStatus(int value) {
            this.value = value;
        }
        public int getValue() {
            return value;
        }
    }

    private String id;
    private String memberId;
    private Integer activityId;
    private Integer teamId;
    private Double exchange;
    private ExchangeStatus exchangeStatus;
    private Date date;
    private Double teamTotal;
    private boolean expired;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Double getExchange() {
        return exchange;
    }

    public void setExchange(Double exchange) {
        this.exchange = exchange;
    }

    public ExchangeStatus getExchangeStatus() {
        return exchangeStatus;
    }

    public void setExchangeStatus(ExchangeStatus exchangeStatus) {
        this.exchangeStatus = exchangeStatus;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getTeamTotal() {
        return teamTotal;
    }

    public void setTeamTotal(Double teamTotal) {
        this.teamTotal = teamTotal;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }
}