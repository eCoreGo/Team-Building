package com.core.service;

import com.core.bean.User;
import com.core.mapper.UserMapper;
import com.core.util.GetSqlSessionFactory;
import org.apache.ibatis.session.SqlSession;

public class UserService {

    @SuppressWarnings("static-access")
    public void addUser(User user) throws RuntimeException {
        SqlSession session = GetSqlSessionFactory.getInstance().getSqlSessionFactory().openSession(true);
        try {
            session.getMapper(UserMapper.class).addUser(user);
        } catch (Exception e) {
            throw new RuntimeException("Fail to add user!", e);
        } finally {
            session.close();
        }
    }

}
