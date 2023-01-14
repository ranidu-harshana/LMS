package com.lms.dao;

import com.lms.Helpers;
import com.lms.beans.User;
import com.lms.beans.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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

    public boolean save(User user) {
        user.setPassword(Helpers.getMd5(user.getPassword()));
        String sql = "INSERT INTO users(name, email, password, address, mobno) VALUES('" + user.getName() + "', '" + user.getEmail() + "', '" + user.getPassword() + "', '"+ user.getAddress() +"', '" + user.getMobno() + "')";
        return template.update(sql) > 0;
    }

    public List<User> usersList() {
        List<User> list = template.query("SELECT * FROM users", new RowMapper<User>() {
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User();

                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setAddress(rs.getString("address"));
                user.setRole(rs.getByte("role"));
                user.setMobno(rs.getString("mobno"));
                user.setCreated_at(rs.getString("created_at"));
                return user;
            }

        });
        return list;
    }
}
