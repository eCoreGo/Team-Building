package com.core.util;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.core.mapper.ActivityAttenderMapper;
import com.core.mapper.ActivityMapper;
import com.core.mapper.ExchangeDetailMapper;
import com.core.mapper.MemberMapper;
import com.core.mapper.TeamMapper;
import com.core.mapper.TeamMemberMapper;

public class GetSqlSessionFactory
{
    private static SqlSessionFactory sqlSessionFactory = null;

    private static GetSqlSessionFactory getSqlSessionFactory = null;

    private GetSqlSessionFactory()
    {
        String rs = "mybatis-config.xml";
        Reader reader = null;
        try
        {
            reader = Resources.getResourceAsReader(rs);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);

        sqlSessionFactory.getConfiguration().addMapper(MemberMapper.class);
        sqlSessionFactory.getConfiguration().addMapper(TeamMapper.class);
        sqlSessionFactory.getConfiguration().addMapper(TeamMemberMapper.class);
        sqlSessionFactory.getConfiguration().addMapper(ActivityMapper.class);
        sqlSessionFactory.getConfiguration().addMapper(ActivityAttenderMapper.class);
        sqlSessionFactory.getConfiguration().addMapper(ExchangeDetailMapper.class);
    }

    public static GetSqlSessionFactory getInstance()
    {
        if (getSqlSessionFactory == null)
            getSqlSessionFactory = new GetSqlSessionFactory();
        return getSqlSessionFactory;
    }

    public static SqlSessionFactory getSqlSessionFactory()
    {
        return sqlSessionFactory;
    }

}