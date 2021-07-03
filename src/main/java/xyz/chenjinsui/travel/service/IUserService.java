package xyz.chenjinsui.travel.service;

import xyz.chenjinsui.travel.domain.User;

public interface IUserService {

    /**
     * 注册用户
     * @param user
     * @return
     */
    boolean register(User user);

    /**
     * 用户激活
     * @param code
     * @return
     */
    boolean active(String code);

    /**
     * 用户登录
     * @param user 待登陆的用户
     * @return 完整的用户信息
     */
    User login(User user);
}
