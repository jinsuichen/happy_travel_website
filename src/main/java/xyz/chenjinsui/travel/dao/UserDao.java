package xyz.chenjinsui.travel.dao;

import xyz.chenjinsui.travel.domain.User;

public interface UserDao {

    /**
     * 根据用户名查询用户信息
     */
    public User findByUserName(String userName);

    /**
     * 用户保存
     * @param user
     */
    public void save(User user);


}
