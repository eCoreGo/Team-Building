package com.core.mapper;

import com.core.bean.TeamMember;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

/**
 * Created by stereomatrix on 2016/3/20.
 */
public interface TeamMemberMapper {

    @Insert("insert into team_member(team_id, member_id, balance, attend_time) values(${teammember.team.id}, ${teammember.member.id}, ${teammember.balance}, ${teammember.attendTime})")
    void join(@Param("teammember")TeamMember teamMember);

    @Delete("delete from team_member where team_id = ${teamId} and member_id = ${memberId}")
    void leave(@Param("teamId") Integer teamId, @Param("memberId") String memberId);
}