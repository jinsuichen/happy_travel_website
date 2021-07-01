package xyz.chenjinsui.travel.service.impl;

import xyz.chenjinsui.travel.dao.UserDao;
import xyz.chenjinsui.travel.dao.impl.UserDaoImpl;
import xyz.chenjinsui.travel.domain.User;
import xyz.chenjinsui.travel.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();


    /**
     * 注册用户
     * @param user
     * @return
     */
    @Override
    public boolean register(User user) {

        //1.根据用户名查询用户对象
        User u = userDao.findByUserName(user.getUserName());
        if(u!=null){
            //用户存在，注册失败
            return false;
        }

        //2.保存用户信息
        userDao.save(user);
        return true;


    }
}
