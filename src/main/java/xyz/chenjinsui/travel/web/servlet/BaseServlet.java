package xyz.chenjinsui.travel.web.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //完成方法分类
        String uri = req.getRequestURI();
        String methodName = uri.substring(uri.lastIndexOf('/') + 1);

        try{

            /*//忽略访问权限修饰符获取方法
            Method method = this.getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);

            //暴力反射
            method.setAccessible(true);  */

            Method method = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);

            method.invoke(this, req, resp);

        }catch(NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }


    /**
     * 将传入的对象序列化为json并写回客户端
     * @param obj 待序列化的对象
     * @param resp resp
     */
    public void writeValue(Object obj, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json;charset=utf-8");
        new ObjectMapper().writeValue(resp.getOutputStream(), obj);
    }


    /**
     * 将传入的对象序列化为json并返回字符串
     * @param obj 待序列化的对象
     * @return 序列化后的json字符串
     */
    public String writeValueAsString(Object obj) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(obj);
    }


}
