package xyz.chenjinsui.travel.service.impl;

import xyz.chenjinsui.travel.dao.IUserDao;
import xyz.chenjinsui.travel.dao.impl.UserDaoImpl;
import xyz.chenjinsui.travel.domain.User;
import xyz.chenjinsui.travel.service.IUserService;
import xyz.chenjinsui.travel.util.MailUtils;
import xyz.chenjinsui.travel.util.UuidUtils;

public class UserServiceImpl implements IUserService {

    private final IUserDao userDao = new UserDaoImpl();


    /**
     * 注册用户
     * @param user 待注册的用户
     * @return 是否成功注册
     */
    @Override
    public boolean register(User user) {

        //1.根据用户名查询用户对象
        User u = userDao.findByUsername(user.getUsername());
        if(u!=null){
            //用户存在，注册失败
            return false;
        }

        //设置为一激活码
        user.setCode(UuidUtils.getUuid());

        //设置激活状态(未激活)
        user.setStatus("N");

        //2.保存用户信息
        userDao.save(user);

        //发送激活邮件
        // TODO 部署项目时更改链接 或 抽取为配置文件
        String content = "<a href='http://localhost/travel_website_war/user/active?code=" + user.getCode() + "'>点击激活【快乐旅游网】</a>";
        MailUtils.sendMail(user.getEmail(), content, "快乐旅游网-激活邮件");
        return true;

    }


    /**
     * 用户激活
     * @param code 激活码
     * @return 是否成功激活
     */
    @Override
    public boolean active(String code) {
        User user = userDao.findByCode(code);
        if(user == null){
            //用户不存在
            return false;
        }
        //进行激活
        userDao.updateStatus(user);
        return true;
    }


    /**
     * 用户登录
     * @param user 待登陆的用户
     * @return 是否登陆成功
     */
    @Override
    public User login(User user) {
        User u = null;
        u = userDao.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        return u;
    }
}
