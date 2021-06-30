package xyz.chenjinsui.travel.service;

import xyz.chenjinsui.travel.domain.User;

public interface UserService {

    /**
     * 注册用户
     * @param user
     * @return
     */
    boolean register(User user);
}
