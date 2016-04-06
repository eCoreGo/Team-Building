package com.core.service;

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
}
