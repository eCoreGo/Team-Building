package com.core.service;

import com.core.bean.Team;
import com.core.bean.TeamMember;
import com.core.mapper.TeamMemberMapper;
import com.core.util.GetSqlSessionFactory;

import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class TeamMemberService {

    @SuppressWarnings("static-access")
    public void join(TeamMember teamMember) throws RuntimeException {
        SqlSession session = GetSqlSessionFactory.getInstance().getSqlSessionFactory().openSession(true);
        try {
            session.getMapper(TeamMemberMapper.class).join(teamMember);
        } catch (Exception e) {
            throw new RuntimeException("Fail to join!", e);
        } finally {
            session.close();
        }
    }

    @SuppressWarnings("static-access")
    public void leave(Integer teamId, String memberId) throws RuntimeException {
        SqlSession session = GetSqlSessionFactory.getInstance().getSqlSessionFactory().openSession(true);
        try {
            session.getMapper(TeamMemberMapper.class).leave(teamId, memberId);
        } catch (Exception e) {
            throw new RuntimeException("Fail to leave!", e);
        } finally {
            session.close();
        }
    }

    @SuppressWarnings("static-access")
    public void leaveAll(Integer teamId) throws RuntimeException {
        SqlSession session = GetSqlSessionFactory.getInstance().getSqlSessionFactory().openSession(true);
        try {
            session.getMapper(TeamMemberMapper.class).leaveAll(teamId);
        } catch (Exception e) {
            throw new RuntimeException("Fail to leave!", e);
        } finally {
            session.close();
        }
    }

    @SuppressWarnings("static-access")
    public List<TeamMember> getTeamMembersByTeamId(Integer teamId) throws RuntimeException {
        SqlSession session = GetSqlSessionFactory.getInstance().getSqlSessionFactory().openSession(true);
        List<TeamMember> teamMembers;
        try {
            teamMembers = session.selectList("com.core.bean.TeamMemberMapper.getTeamMembersByTeamId",teamId);
        } catch (Exception e) {
            throw new RuntimeException("Fail to get team members!", e);
        } finally {
            session.close();
        }
        return teamMembers;
    }
    
    public List<TeamMember> getTeamMembersByMemberId(String memberId) throws RuntimeException {
        List<TeamMember> teamMembers;
        SqlSession session = GetSqlSessionFactory.getInstance().getSqlSessionFactory().openSession(true);
        try {
                teamMembers = session.selectList("com.core.bean.TeamMemberMapper.getTeamMembersByMemberId", memberId);
        } catch (Exception e) {
            throw new RuntimeException("Fail to get teamMembers!", e);
        } finally {
            session.close();
        }
        return teamMembers;
    }

    @SuppressWarnings("static-access")
    public TeamMember getTeamMemberInfo(Integer teamId, String memberId) throws RuntimeException {
        SqlSession session = GetSqlSessionFactory.getInstance().getSqlSessionFactory().openSession(true);
        TeamMember teamMember;
        try {
        	teamMember = session.getMapper(TeamMemberMapper.class).getTeamMemberInfo(teamId, memberId);
        } catch (Exception e) {
            throw new RuntimeException("Fail to get team members!", e);
        } finally {
            session.close();
        }
        return teamMember;
    }
    
    @SuppressWarnings("static-access")
    public List<Team> getTeams(String memberId) throws RuntimeException {
        SqlSession session = GetSqlSessionFactory.getInstance().getSqlSessionFactory().openSession(true);
        List<Team> teams;
        try {
        	teams = session.getMapper(TeamMemberMapper.class).getTeams(memberId);
        } catch (Exception e) {
            throw new RuntimeException("Fail to get team members!", e);
        } finally {
            session.close();
        }
        return teams;
    }

    @SuppressWarnings("static-access")
    public void updateMemberFee(String memberId, Integer teamId, Double delta) throws RuntimeException {
        SqlSession session = GetSqlSessionFactory.getInstance().getSqlSessionFactory().openSession(true);
        try {
            session.getMapper(TeamMemberMapper.class).updateMemberFee(memberId, teamId, delta);
        } catch (Exception e) {
            throw new RuntimeException("Fail to leave!", e);
        } finally {
            session.close();
        }
    }
}
