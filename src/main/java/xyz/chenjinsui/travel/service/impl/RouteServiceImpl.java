package xyz.chenjinsui.travel.service.impl;

import xyz.chenjinsui.travel.dao.IRouteDao;
import xyz.chenjinsui.travel.dao.impl.RouteDaoImpl;
import xyz.chenjinsui.travel.domain.PageBean;
import xyz.chenjinsui.travel.domain.Route;
import xyz.chenjinsui.travel.service.IRouteService;

import java.util.List;

public class RouteServiceImpl implements IRouteService {

    IRouteDao routeDao = new RouteDaoImpl();

    @Override
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize, String rname) {

        PageBean<Route> pb = new PageBean<Route>();
        pb.setCurrentPage(currentPage);
        pb.setPageSize(pageSize);
        int totalCount = routeDao.findTotalCount(cid, rname);
        pb.setTotalCount(totalCount);


        //当前页显示的数据集合
        int start = (currentPage - 1) * pageSize;
        List<Route> list = routeDao.findByPage(cid, start, pageSize, rname);
        pb.setList(list);

        //设置总页数
        int totalPage = totalCount % pageSize == 0 ? totalCount/pageSize : totalCount/pageSize+1;
        pb.setTotalPage(totalPage);

        return pb;

    }


}
