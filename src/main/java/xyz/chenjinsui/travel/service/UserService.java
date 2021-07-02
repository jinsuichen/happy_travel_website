package xyz.chenjinsui.travel.service;

import xyz.chenjinsui.travel.dao.UserDao;
import xyz.chenjinsui.travel.dao.impl.UserDaoImpl;
import xyz.chenjinsui.travel.domain.User;

public interface UserService {

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
}
