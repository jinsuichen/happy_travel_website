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

@WebServlet("/registerUserServlet")
public class RegisterUserServlet extends HttpServlet {

    /**
     *  1.获取数据
     *  2.封装User对象
     *  3.调用service完成注册
     *  4.根据service的返回信息
     *      (1)将提示信息转为json
     *      (2)设置响应头contentType
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        UserService service = new UserServiceImpl();
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
        
        //将resultInfo序列化为json写回客户端
        resp.setContentType("application/json;charset=utf-8");
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(resultInfo);
        resp.getWriter().write(json);
        
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
