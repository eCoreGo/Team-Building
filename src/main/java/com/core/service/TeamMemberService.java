package com.core.service;

import com.core.bean.TeamMember;
import com.core.mapper.TeamMemberMapper;
import com.core.util.GetSqlSessionFactory;
import org.apache.ibatis.session.SqlSession;

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

}
