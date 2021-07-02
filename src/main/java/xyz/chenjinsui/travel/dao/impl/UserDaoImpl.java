package xyz.chenjinsui.travel.dao.impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import xyz.chenjinsui.travel.dao.UserDao;
import xyz.chenjinsui.travel.domain.User;
import xyz.chenjinsui.travel.util.JDBCUtils;

public class UserDaoImpl implements UserDao {

    private final JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public User findByUsername(String username) {
        User user = null;
        try{
            String sql = "select * from tab_user where username = ?";
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);
        }catch (Exception ignored) {
        }
        return user;
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        User user = null;
        try{
            String sql = "select * from tab_user where username = ? AND password = ?";
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username, password);
        }catch (Exception ignored) {
        }
        return user;
    }

    @Override
    public User findByCode(String code){
        User user = null;
        try{
            String sql = "select * from tab_user where code = ?";
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), code);
        }catch (Exception ignored){
        }
        return user;
    }

    @Override
    public void save(User user) {
        String sql = "insert into tab_user(username, password, name, birthday, gender, telephone, email,status, code) values(?,?,?,?,?,?,?,?,?)";
        template.update(sql,
                user.getUsername(),
                user.getPsw(),
                user.getRealname(),
                user.getBirthday(),
                user.getGender(),
                user.getTel(),
                user.getEmail(),
                user.getStatus(),
                user.getCode());
    }

    @Override
    public void updateStatus(User user) {
        String code = user.getCode();
        String sql = "UPDATE tab_user SET status='Y' WHERE code = ?";
        template.update(sql, code);
    }


}
