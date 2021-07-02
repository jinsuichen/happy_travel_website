package xyz.chenjinsui.travel.dao;

import xyz.chenjinsui.travel.domain.User;

public interface UserDao {

    /**
     * 根据用户名查询用户信息
     * @param userName 用户名
     * @return 查询到的用户
     */
    public User findByUsername(String userName);

    /**
     * 根据用户名查询用户信息
     * @param username 用户名
     * @param password 密码
     * @return 查询到的用户
     */
    public User findByUsernameAndPassword(String username, String password);

    /**
     * 根据激活码查询用户信息
     * @param code 激活码
     * @return  查询到的用户
     */
    public User findByCode(String code);

    /**
     * 用户保存
     * @param user
     */
    public void save(User user);

    /**
     * 将用户的激活状态从未激活改为已激活
     * @param user 待更改状态的用户
     */
    public void updateStatus(User user);




}
