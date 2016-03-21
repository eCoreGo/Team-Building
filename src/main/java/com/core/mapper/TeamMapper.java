package com.core.mapper;

import com.core.bean.Team;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by stereomatrix on 2016/3/20.
 */
public interface TeamMapper {

    @Select("select * from team")
    List<Team> getTeams();

    @Insert("insert into team(id, temp, name, total_foundation, total_user_balance, creation_time) values(#{team.id}, #{team.temp}, #{team.name}, #{team.totalFoundation}, #{team.totalUserBalance}, #{team.creationTime})")
    void addTeam(@Param(value = "team") Team team);

    @Update("update team set name=${team.name}, total_foundation=${team.totalFoundation}, total_user_balance=${team.totalUserBalance} where id = ${team.id}")
    void updateTeam(@Param(value = "team") Team team);

    @Delete("delete from team where id = ${id}")
    void removeTeam(@Param(value = "id") Integer id);
}
