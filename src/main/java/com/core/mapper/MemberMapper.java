package com.core.mapper;

import com.core.bean.Member;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by stereomatrix on 2016/1/20.
 */
public interface MemberMapper {

    @Select("select * from member")
    List<Member> getMembers();

    @Insert("insert into member(id, name, phone) values(${member.id}, ${member.name}, ${member.phone})")
    void addMember(@Param(value = "member") Member member);

    @Select("select id, name, phone from member where id = ${id}")
    Member getMemberById(@Param("id") String id);

}