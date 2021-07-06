package xyz.chenjinsui.travel.service.impl;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;
import xyz.chenjinsui.travel.dao.ICategoryDao;
import xyz.chenjinsui.travel.dao.impl.CategoryDaoImpl;
import xyz.chenjinsui.travel.domain.Category;
import xyz.chenjinsui.travel.service.ICategoryService;
import xyz.chenjinsui.travel.util.JedisUtils;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CategoryServiceImpl implements ICategoryService {

    private final ICategoryDao categoryDao  = new CategoryDaoImpl();

    /**
     * 查询所有分类
     * @return 所有Category分类组成一个列表
     */
    @Override
    public List<Category> findAll() {

        //FIXME 名字太乱了 需要修改

        //从redis中查询
        Jedis jedis = JedisUtils.getJedis();

        //查询cid和cname
        Set<Tuple> categories = jedis.zrangeWithScores("category", 0, -1);
        List<Category> cs = null; //待返回的数据

        if(categories == null || categories.size() == 0){
            //若无数据，则从MySQL中查询得到list，储存到redis
            cs = categoryDao.findAll();
            for(var category : cs){
                jedis.zadd("category", category.getCid(), category.getCname());
            }

        }else{
            //若有数据，将查询到的set转换为list
            cs = new ArrayList<Category>();
            for (Tuple t : categories) {
                Category c = new Category();
                c.setCname(t.getElement());
                c.setCid((int) t.getScore());
                cs.add(c);
            }
        }



        jedis.close();
        return cs;
    }
}
