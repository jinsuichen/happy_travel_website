package xyz.chenjinsui.travel.dao.impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import xyz.chenjinsui.travel.dao.IRouteDao;
import xyz.chenjinsui.travel.domain.Route;
import xyz.chenjinsui.travel.util.JdbcUtils;
import xyz.chenjinsui.travel.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class RouteDaoImpl implements IRouteDao {

    private JdbcTemplate template = new JdbcTemplate(JdbcUtils.getDataSource());


    @Override
    public int findTotalCount(int cid, String rname) {
        try {
            rname = StringUtils.decode(rname);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String sql = " select count(*) from tab_route where 1=1 ";
        List params = new ArrayList(); // 条件
        if(cid !=0 ) {
            sql += " and cid = ? ";
            params.add(cid);
        }
        if(rname!=null && rname.length() > 0) {
            sql += " and rname like ? ";
            params.add("%" + rname+ "%");
        }
        return template.queryForObject(sql, Integer.class, params.toArray());
    }

    @Override
    public List<Route> findByPage(int cid, int start, int pageSize, String rname) {

        try {
            rname = StringUtils.decode(rname);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        String sql = " select * from tab_route where 1=1 " ;
        List params = new ArrayList(); // 条件
        if(cid !=0 ) {
            sql += " and cid = ? ";
            params.add(+cid);
        }
        if(rname!=null && rname.length() > 0) {
            sql += " and rname like ? ";
            params.add("%" + rname + "%");
        }
        sql += " limit ?, ? ";//分页条件
        params.add(start);
        params.add(pageSize);
        return template.query(sql, new BeanPropertyRowMapper<Route>(Route.class), params.toArray());
    }



}
