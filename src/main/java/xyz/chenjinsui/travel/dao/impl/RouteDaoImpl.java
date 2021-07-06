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

    private final JdbcTemplate template = new JdbcTemplate(JdbcUtils.getDataSource());


    @Override
    public int findTotalCount(int cid, String rname) {
        String rnameU8 = "";
        try {
            rnameU8 = StringUtils.decode(rname);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String sql = " select count(*) from tab_route where 1=1 ";
        List params = new ArrayList(); // 条件列表
        if(cid !=0 ) {
            sql += " and cid = ? ";
            params.add(cid);
        }
        if(rname!=null && rname.length() > 0) {
            sql += " and ( rname like ? ";
            sql += " or rname like ? )";
            params.add("%" + rname+ "%");
            params.add("%" + rnameU8 + "%");
        }
        return template.queryForObject(sql, Integer.class, params.toArray());
    }

    @Override
    public List<Route> findByPage(int cid, int start, int pageSize, String rname) {
        String rnameU8 = "";

        try {
            rnameU8 = StringUtils.decode(rname);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        String sql = " select * from tab_route where 1=1 " ;
        List params = new ArrayList(); // 条件
        if(cid !=0 ) {
            sql += " and cid = ? ";
            params.add(cid);
        }
        if(rname!=null && rname.length() > 0) {
            sql += " and ( rname like ? ";
            sql += " or rname like ? )";
            params.add("%" + rname+ "%");
            params.add("%" + rnameU8 + "%");
        }
        sql += " limit ?, ? ";//分页条件
        params.add(start);
        params.add(pageSize);
        System.out.println(sql);
        params.forEach(System.out::println);
        return template.query(sql, new BeanPropertyRowMapper<Route>(Route.class), params.toArray());
    }



}
