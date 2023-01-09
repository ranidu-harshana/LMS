package com.lms.dao;

import com.lms.beans.Book;
import com.lms.beans.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDao {
    JdbcTemplate template;

    @Autowired
    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    public Boolean validateUser(String email, String password) {
        Boolean result;
        try {
            User user = findUser(email);
            result = password.equals(user.getPassword());
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    public User findUser(String email) {
        String sql = "SELECT * FROM users where email=?";
        return template.queryForObject(sql, new Object[]{email},new BeanPropertyRowMapper<User>(User.class));
    }
}
