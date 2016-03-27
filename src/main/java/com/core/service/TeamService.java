package com.core.service;

import com.core.bean.Team;
import com.core.mapper.TeamMapper;
import com.core.util.GetSqlSessionFactory;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class TeamService {

    @SuppressWarnings("static-access")
    public void addTeam(Team team) throws RuntimeException {
        SqlSession session = GetSqlSessionFactory.getInstance().getSqlSessionFactory().openSession(true);
        try {
            session.getMapper(TeamMapper.class).addTeam(team);
        } catch (Exception e) {
            throw new RuntimeException("Fail to add team!", e);
        } finally {
            session.close();
        }
    }

    @SuppressWarnings("static-access")
    public List<Team> getTeams() throws RuntimeException {
        List<Team> teams;
        SqlSession session = GetSqlSessionFactory.getInstance().getSqlSessionFactory().openSession(true);
        try {
            teams = session.getMapper(TeamMapper.class).getTeams();
        } catch (Exception e) {
            throw new RuntimeException("Fail to get teams!", e);
        } finally {
            session.close();
        }
        return teams;
    }

    @SuppressWarnings("static-access")
    public Team getTeamById(Integer id) throws RuntimeException {
        Team team;
        SqlSession session = GetSqlSessionFactory.getInstance().getSqlSessionFactory().openSession(true);
        try {
            team = session.getMapper(TeamMapper.class).getTeamById(id);
        } catch (Exception e) {
            throw new RuntimeException("Fail to get teams!", e);
        } finally {
            session.close();
        }
        return team;
    }

    @SuppressWarnings("static-access")
    public void updateTeam(Team team) throws RuntimeException {
        SqlSession session = GetSqlSessionFactory.getInstance().getSqlSessionFactory().openSession(true);
        try {
            session.getMapper(TeamMapper.class).updateTeam(team);
        } catch (Exception e) {
            throw new RuntimeException("Fail to update team!", e);
        } finally {
            session.close();
        }
    }

    @SuppressWarnings("static-access")
    public void removeTeam(Integer id) throws RuntimeException {
        SqlSession session = GetSqlSessionFactory.getInstance().getSqlSessionFactory().openSession(true);
        try {
            session.getMapper(TeamMapper.class).removeTeam(id);
        } catch (Exception e) {
            throw new RuntimeException("Fail to remove team!", e);
        } finally {
            session.close();
        }
    }

}
