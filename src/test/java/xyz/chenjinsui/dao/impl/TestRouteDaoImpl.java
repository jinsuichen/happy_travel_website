package xyz.chenjinsui.dao.impl;

import org.junit.Test;
import xyz.chenjinsui.travel.dao.IRouteDao;
import xyz.chenjinsui.travel.dao.impl.RouteDaoImpl;
import xyz.chenjinsui.travel.domain.Route;
import xyz.chenjinsui.travel.service.IRouteService;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestRouteDaoImpl {

    @Test
    public void testFindTotalCount(){
        IRouteDao dao = new RouteDaoImpl();
        assertEquals(1, dao.findTotalCount(1, "南"));

    }

    @Test
    public void testFindByPage(){
        IRouteDao dao = new RouteDaoImpl();
        List<Route> list = dao.findByPage(1, 0, 5, "南");
        System.out.println("test");
        list.forEach(System.out::println);
    }
}
