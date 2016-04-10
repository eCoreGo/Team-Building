package com.core.mapper;

import com.core.bean.Member;
import com.core.bean.Team;
import com.core.bean.TeamMember;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by stereomatrix on 2016/3/20.
 */
public interface TeamMemberMapper {

    @Insert("insert into team_member(team_id, member_id, balance, attend_time) values(${teammember.team.id}, ${teammember.member.id}, ${teammember.balance}, #{teammember.attendTime})")
    void join(@Param("teammember")TeamMember teamMember);

    @Delete("delete from team_member where team_id = ${teamId} and member_id = ${memberId}")
    void leave(@Param("teamId") Integer teamId, @Param("memberId") String memberId);

    @Delete("delete from team_member where team_id = ${teamId}")
    void leaveAll(@Param("teamId") Integer teamId);

    @Select("select b.* from team_member a, member b where a.member_id = b.id and a.team_id = ${teamId}")
    List<Member> getMembers(@Param("teamId") Integer teamId);

    @Select("select b.id as id, b.name as name, b.description as description, b.total_user_balance as totalUserBalance, b.total_foundation as totalFoundation from team_member a, team b where a.team_id = b.id and a.member_id = ${memberId}")
    List<Team> getTeams(@Param("memberId") String memberId);
    
    @Select("select * from team_member where team_id = ${teamId} and member_id = ${memberId}")
    TeamMember getTeamMemberInfo(@Param("teamId") Integer teamId, @Param("memberId") String memberId);

    @Update("update team_member set balance = balance + ${delta} where team_id = ${teamId} and member_id = ${memberId};")
    void updateMemberFee(@Param("memberId") String memberId, @Param("teamId") Integer teamId, @Param("delta") Double delta);
}
