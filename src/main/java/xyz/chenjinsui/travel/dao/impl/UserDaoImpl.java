package xyz.chenjinsui.travel.dao.impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import xyz.chenjinsui.travel.dao.UserDao;
import xyz.chenjinsui.travel.domain.User;
import xyz.chenjinsui.travel.util.JDBCUtils;

public class UserDaoImpl implements UserDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public User findByUserName(String userName) {
        User user = null;

        try{
            //定义sql
            String sql = "select * from tab_user where username = ?";
            //执行sql
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), userName);

        }catch (Exception e) {

        }
        return user;

    }

    @Override
    public void save(User user) {

        //debug
        System.out.println(user);

        //定义sql
        String sql = "insert into tab_user(username, password, name, birthday, gender, telephone, email,status, code) values(?,?,?,?,?,?,?,?,?)";
        //执行sql
        template.update(sql, user.getUsername(),
                user.getPsw(),
                user.getRealname(),
                user.getBirthday(),
                user.getGender(),
                user.getTel(),
                user.getEmail(),
                user.getStatus(),
                user.getCode());
    }
}
