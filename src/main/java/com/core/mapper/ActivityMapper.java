package com.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.core.bean.Activity;
import com.core.bean.ActivityAttender;

/**
 * Created by stereomatrix on 2016/1/20.
 */
public interface ActivityMapper {

    @Select("select id, name, total_cost as totalCost, total_foundation_cost as totalFoundationCost, team_id as teamId, description, status, start_time as startTime, end_time as endTime from activity where status = 2 and team_id = #{teamId}")
    List<Activity> getOngoingActivities(@Param(value = "teamId") Integer teamID);

    @Select("select id, name, total_cost as totalCost, total_foundation_cost as totalFoundationCost, team_id, description, status, start_time as startTime, end_time as endTime from activity where team_id = #{teamId}")
    List<Activity> getAllActivitiesByTeamId(@Param(value = "teamId") Integer teamID);

    @Select("select id, name, total_cost as totalCost, total_foundation_cost as totalFoundationCost, team_id as teamId, description, status, start_time as startTime, end_time as endTime from activity where team_id in(#{teamIds})")
    List<Activity> getAllActivitiesByTeamIds(@Param(value = "teamIds") String teamIDs);

    @Insert("insert into activity(name, total_cost, total_foundation_cost, team_id, description, status, start_time, end_time) values(#{activity.name}, "
    		+ "#{activity.totalCost}, #{activity.totalFoundationCost}, "
    		+ "#{activity.team.id}, #{activity.description}, #{activity.status},#{activity.startTime} , #{activity.endTime})")
    @Options(useGeneratedKeys=true, keyProperty="activity.id")
    int addActivity(@Param(value = "activity") Activity activity);

    @Select("select id, name, total_cost as totalCost, total_foundation_cost as totalFoundationCost, team_id, description, status, start_time as startTime, end_time as endTime from activity where id = #{id}")
    Activity getActivitiesById(@Param(value = "id") Integer id);
    
	@Update("update activity set status=#{status} where id=#{activityId}")
	void updateActivityStatus(@Param(value = "activityId") Integer activityId,@Param(value = "status") Integer status);


}
