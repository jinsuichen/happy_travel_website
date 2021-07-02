package xyz.chenjinsui.travel.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;
import xyz.chenjinsui.travel.domain.ResultInfo;
import xyz.chenjinsui.travel.domain.User;
import xyz.chenjinsui.travel.service.UserService;
import xyz.chenjinsui.travel.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //TODO 增加"记住账户"功能
        //TODO 增加"手机验证码"功能

        //封装bean
        User user = new User();
        Map<String, String[]> parameterMap = req.getParameterMap();
        try {
            BeanUtils.populate(user, parameterMap);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        //进行注册，获得user
        UserService service = new UserServiceImpl();
        User u = service.login(user);
        ResultInfo info = new ResultInfo();

        //生成回写数据
        if(u == null){
            //未找到用户
            System.out.println("1");
            info.setFlag(false);
            info.setErrorMsg("用户名或密码错误");
        } else if("N".equals(u.getStatus())){
            //未激活
            System.out.println(2);
            info.setFlag(false);
            info.setErrorMsg("未进行激活，请进行邮箱激活");
        } else if("Y".equals(u.getStatus())){
            //正常
            System.out.println(3);
            info.setFlag(true);
        }

        //debug
        System.out.println(info.isFlag());
        System.out.println(info.getErrorMsg());

        //回写数据
        resp.setContentType("application/json;charset=utf-8");
        new ObjectMapper().writeValue(resp.getOutputStream(), info);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
