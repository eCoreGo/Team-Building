package com.core.mapper;

/**
 * Created by wanglan on 16/3/29.
 */

import com.core.bean.ExchangeDetail;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

public interface ExchangeDetailMapper {

    /*@Insert("insert into exchange_detail(id, member_id, activity_id, team_id, exchange, exchange_status, date, team_total, expired) " +
            "values(${exchangedetail.id}, ${exchangedetail.memberId}, ${exchangedetail.activityId}, " +
            "${exchangedetail.teamId}, ${exchangedetail.exchange}, ${exchangedetail.exchangeStatus}" +
            "${exchangedetail.date}, ${exchangedetail.teamTotal}), ${exchangedetail.expired}")*/
    @Insert("insert into exchange_detail(id, member_id, activity_id, team_id, exchange, exchange_status, date, team_total, expired) "
            + "values(id, memberId, activityId, teamId, exchange, exchangeStatus, date, teamTotal, expired)")
    void addExchangeDetail(final List<ExchangeDetail> exchangeDetail);

    @Select("select * from exchange_detail where memberId = ${memberId} and teamId = ${teamId} and" +
            "date >= ${from} and date <= ${to}")
    List<ExchangeDetail> getExchangeDetails(String memberId, Integer teamId, Date from, Date to);

    @Select("select * from exchange_detail where teamId = ${teamId} and expired = false")
    ExchangeDetail getUnexpiredTeamDetail(Integer teamId);
}