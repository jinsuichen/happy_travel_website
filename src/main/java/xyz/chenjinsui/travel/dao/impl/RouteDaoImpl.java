package xyz.chenjinsui.travel.dao.impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import xyz.chenjinsui.travel.dao.IRouteDao;
import xyz.chenjinsui.travel.domain.Route;
import xyz.chenjinsui.travel.util.JdbcUtils;

import java.util.List;

public class RouteDaoImpl implements IRouteDao {

    private JdbcTemplate template = new JdbcTemplate(JdbcUtils.getDataSource());


    @Override
    public int findTotalCount(int cid) {
        String sql = "select count(*) from tab_route where cid = ?";
        return template.queryForObject(sql, Integer.class, cid);
    }

    @Override
    public List<Route> findByPage(int cid, int start, int pageSize) {
        String sql = "select * from tab_route where cid = ? limit ? , ?";
        return template.query(sql, new BeanPropertyRowMapper<Route>(Route.class), cid, start, pageSize);
    }



}
