package com.core.util;

import com.core.mapper.MemberMapper;
import com.core.mapper.TeamMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

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