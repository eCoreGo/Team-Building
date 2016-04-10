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

    @Select("select id, member_id as memberId, activity_id as activityId, team_id as teamId, exchange, exchange_status as exchangeStatus, date, team_total as teamTotal, expired "
                + "from exchange_detail where member_id = #{memberId} and team_id = #{teamId} and " + "date >= #{from} and date <= #{to} order by date desc")
    List<ExchangeDetail> getExchangeDetails(@Param(value = "memberId") String memberId, @Param(value = "teamId") Integer teamId, @Param(value = "from") Date from, @Param(value = "to") Date to);

    @Select("select id, member_id as memberId, activity_id as activityId, team_id as teamId, exchange, exchange_status as exchangeStatus, date, team_total as teamTotal, expired "
                + "from exchange_detail where member_id = #{memberId} and team_id = #{teamId} order by date desc")
    List<ExchangeDetail> getExchangeDetailsByMemberTeamId(@Param(value = "memberId") String memberId, @Param(value = "teamId") Integer teamId);

    @Select("select id, member_id as memberId, activity_id as activityId, team_id as teamId, exchange, exchange_status as exchangeStatus, date, team_total as teamTotal, expired "
                + "from exchange_detail where member_id = #{memberId} order by date desc" )
    List<ExchangeDetail> getExchangeDetailsByMemberId(@Param(value = "memberId") String memberId);

    @Select("select id, member_id as memberId, activity_id as activityId, team_id as teamId, exchange, exchange_status as exchangeStatus, date, team_total as teamTotal, expired "
                + "from exchange_detail where member_id = #{memberId} and date >= #{from} and date <= #{to} order by date desc")
    List<ExchangeDetail> getExchangeDetailsByMemberIdDuration(@Param(value = "memberId") String memberId, @Param(value = "from") Date from, @Param(value = "to") Date to);

    @Select("select id, member_id, activity_id, team_id, exchange, exchange_status, date, team_total, expired"
                + " as id, memberId, activityId, teamId, exchange, exchangeStatus, date, teamTotal, expired"
                + "from exchange_detail where team_id = #{teamId} and expired = false order by date desc")
    ExchangeDetail getUnexpiredTeamDetail(@Param(value = "teamId") Integer teamId);
}