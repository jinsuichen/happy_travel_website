package xyz.chenjinsui.travel.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;
import xyz.chenjinsui.travel.domain.ResultInfo;
import xyz.chenjinsui.travel.domain.User;
import xyz.chenjinsui.travel.service.IUserService;
import xyz.chenjinsui.travel.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author FengLing
 */
@WebServlet("/user/*")
public class UserServlet extends BaseServlet {


    private final IUserService service = new UserServiceImpl();

    /**
     * 注册功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取数据
        Map<String, String[]> parameterMap = req.getParameterMap();


        //debug 遍历从前端获取的表单数据
        /*Set<String> keySet = parameterMap.keySet();
        for(var key : keySet){
            System.out.println(key + " : " + Arrays.toString(parameterMap.get(key)));
        }*/

        //封装对象
        User user = new User();
        try {
            BeanUtils.populate(user, parameterMap);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        //调用service完成注册
        boolean flag = service.register(user);

        //响应结果
        ResultInfo resultInfo = new ResultInfo();
        if(flag){
            //注册成功
            resultInfo.setFlag(true);
        }else{
            //注册失败
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("注册失败！");
        }

        //TODO
        // 1.更改返回方式
        // 2.使用BaseServlet中的方法进行优化
        //将resultInfo序列化为json写回客户端
        resp.setContentType("application/json;charset=utf-8");
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(resultInfo);
        resp.getWriter().write(json);
    }


    /**
     * 登录功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        User u = service.login(user);
        ResultInfo info = new ResultInfo();

        //debug
        //为了可以在控制台上显示登录信息
        int loginStatus = 0;

        //生成回写数据
        if(u == null){
            //未找到用户
            loginStatus = 1;
            info.setFlag(false);
            info.setErrorMsg("用户名或密码错误");
        } else if("N".equals(u.getStatus())){
            //未激活
            loginStatus = 2;
            info.setFlag(false);
            info.setErrorMsg("未进行激活，请进行邮箱激活");
        } else if("Y".equals(u.getStatus())){
            //正常
            loginStatus = 3;
            info.setFlag(true);
            req.getSession().setAttribute("user", u);
            System.out.println(u);
        }

        //debug
        //在控制台上显示登录信息
        System.out.println("");
        System.out.println("有用户尝试登陆：");
        System.out.println("状态编号: " + loginStatus);
        System.out.println("状态信息: " + info.getErrorMsg());
        System.out.println("是否成功: " + info.isFlag());
        System.out.println("");

        //回写数据
        writeValue(info,resp);
    }


    /**
     * 查询单个对象
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void findOne(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //从Session中获取登录用户
        Object user = req.getSession().getAttribute("user");

        /*//debug
        System.out.println(user);
        System.out.println((User)user);*/

        //将user写回客户端
        writeValue((User)user, resp);

    }


    /**
     * 退出功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void exit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //销毁session
        req.getSession().invalidate();

        //跳转至主页
        resp.sendRedirect(req.getContextPath()+"/index.html");
    }


    /**
     * 激活功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void active(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取激活码，完成激活
        String code= req.getParameter("code");
        if(code != null){
            boolean flag = service.active(code);
            String msg = null;
            if(flag){
                msg = "激活成功，请<a href=' " + req.getContextPath() + "/login.html'>登录</a>";
            }else{
                msg = "激活失败，请联系管理员";
            }
            //FIXME 此处可能有误
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().write(msg);
        }
    }

}
