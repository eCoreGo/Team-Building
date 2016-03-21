package com.core.mapper;

import com.core.bean.Activity;
import com.core.bean.Member;
import com.core.bean.Team;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by stereomatrix on 2016/1/20.
 */
public interface ActivityMapper {

    @Select("select * from activity where status = 2 and teamID = ${teamID}")
    List<Activity> getOngoingActivities(String teamID);

    @Insert("insert into activity(id, name, totalCost, totalFoundationCost, time, description, status) values(${activity.id}, ${activity.name}, ${activity.totalCost}, ${activity.totalFoundationCost}, ${activity.time}, ${activity.description}, ${activity.status})")
    void addActivity(@Param(value = "activity") Activity activity);


}
