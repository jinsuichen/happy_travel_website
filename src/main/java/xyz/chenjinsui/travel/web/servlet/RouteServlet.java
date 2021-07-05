package xyz.chenjinsui.travel.web.servlet;

import xyz.chenjinsui.travel.dao.IRouteDao;
import xyz.chenjinsui.travel.domain.PageBean;
import xyz.chenjinsui.travel.domain.Route;
import xyz.chenjinsui.travel.service.ICategoryService;
import xyz.chenjinsui.travel.service.IRouteService;
import xyz.chenjinsui.travel.service.impl.CategoryServiceImpl;
import xyz.chenjinsui.travel.service.impl.RouteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

/**
 * @author FengLing
 */
@WebServlet("/route/*")
public class RouteServlet extends BaseServlet{

    private IRouteService routeService = new RouteServiceImpl();

    /**
     * 分页查询
     */
    public void pageQuery(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String cidStr = req.getParameter("cid");
        String pageSizeStr = req.getParameter("pageSize");
        String currentPageStr = req.getParameter("currentPage");
        String rname = req.getParameter("rname");


        int cid = 0;
        int currentPage = 0;
        int pageSize = 0;

        if(cidStr != null && cidStr.length()>0){
            cid = Integer.parseInt(cidStr);
        }

        if(currentPageStr != null && currentPageStr.length()>0){
            currentPage = Integer.parseInt(currentPageStr);
        }else{
            //如果不传递页码，则默认为第一页
            currentPage = 1;
        }


        if(pageSizeStr != null && pageSizeStr.length()>0){
            pageSize = Integer.parseInt(pageSizeStr);
        }else{
            //如果不传递页码，则默认显示5条
            pageSize = 5;
        }

        PageBean<Route> routePageBean = routeService.pageQuery(cid, currentPage, pageSize, rname);
        writeValue(routePageBean, resp);
        System.out.println(routePageBean);


    }
}
