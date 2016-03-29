package com.core.service;

import com.core.bean.Member;
import com.core.mapper.MemberMapper;
import com.core.util.GetSqlSessionFactory;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class MemberService {

    @SuppressWarnings("static-access")
    public void addMember(Member member) throws RuntimeException {
        SqlSession session = GetSqlSessionFactory.getInstance().getSqlSessionFactory().openSession(true);
        try {
            session.getMapper(MemberMapper.class).addMember(member);
        } catch (Exception e) {
            throw new RuntimeException("Fail to add member!", e);
        } finally {
            session.close();
        }
    }
    
    @SuppressWarnings("static-access")
    public void updateMemberById(String id, String name, String phone) throws RuntimeException {
        SqlSession session = GetSqlSessionFactory.getInstance().getSqlSessionFactory().openSession(true);
        try {
            session.getMapper(MemberMapper.class).updateMemberById(id, name, phone);
        } catch (Exception e) {
            throw new RuntimeException("Fail to update member!", e);
        } finally {
            session.close();
        }
    }

    @SuppressWarnings("static-access")
    public List<Member> getMembers() throws RuntimeException {
        List<Member> members;
        SqlSession session = GetSqlSessionFactory.getInstance().getSqlSessionFactory().openSession(true);
        try {
            members = session.getMapper(MemberMapper.class).getMembers();
        } catch (Exception e) {
            throw new RuntimeException("Fail to get members!", e);
        } finally {
            session.close();
        }
        return members;
    }

    @SuppressWarnings("static-access")
    public Member getMemberById(String id) throws RuntimeException {
        Member member;
        SqlSession session = GetSqlSessionFactory.getInstance().getSqlSessionFactory().openSession(true);
        try {
            member = session.getMapper(MemberMapper.class).getMemberById(id);
        } catch (Exception e) {
            throw new RuntimeException("Fail to get members!", e);
        } finally {
            session.close();
        }
        return member;
    }

}
