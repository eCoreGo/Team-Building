package com.core.mapper;

import com.core.bean.Team;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by stereomatrix on 2016/3/20.
 */
public interface TeamMapper {

    @Select("select id as id, name as name, description as description, total_user_balance as totalUserBalance, total_foundation as totalFoundation from team")
    List<Team> getTeams();

    @Select("select id as id, name as name, description as description, total_user_balance as totalUserBalance, total_foundation as totalFoundation from team where name like '%${name}%'")
    List<Team> getTeamsByNameWildCard(@Param(value = "name") String name);

    @Select("select id as id, name as name, description as description, total_user_balance as totalUserBalance, total_foundation as totalFoundation from team where id = ${id}")
    Team getTeamById(@Param(value = "id") Integer id);

    @Select("select id as id, name as name, description as description, total_user_balance as totalUserBalance, total_foundation as totalFoundation from team where name = '${name}'")
    Team getTeamByName(@Param(value = "name") String name);

    @Insert("insert into team(temp, name, description, total_foundation, total_user_balance, creation_time) values(#{team.temp}, #{team.name}, #{team.description}, #{team.totalFoundation}, #{team.totalUserBalance}, #{team.creationTime})")
    void addTeam(@Param(value = "team") Team team);

    @Update("update team set name=${team.name}, description=${team.description}, total_foundation=${team.totalFoundation}, total_user_balance=${team.totalUserBalance} where id = ${team.id}")
    void updateTeam(@Param(value = "team") Team team);

    @Delete("delete from team where id = ${id}")
    void removeTeam(@Param(value = "id") Integer id);
}
