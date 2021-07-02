package xyz.chenjinsui.travel.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class CharacterFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //将父类接口转为子接口
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 获取请求方法
        String method = request.getMethod();

        //解决POST请求中中文乱码的问题
        if(method.equalsIgnoreCase("post")){
            request.setCharacterEncoding("utf-8");
        }

        // 处理响应乱码
        response.setContentType("text/html;charset=utf-8");
        filterChain.doFilter(request, response);

    }

    @Override
    public void destroy() {

    }
}
