package xyz.chenjinsui.travel.service;

import xyz.chenjinsui.travel.domain.PageBean;
import xyz.chenjinsui.travel.domain.Route;

public interface IRouteService {
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize, String rname);
}
