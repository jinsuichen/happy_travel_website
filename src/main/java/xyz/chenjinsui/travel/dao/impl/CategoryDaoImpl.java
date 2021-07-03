package xyz.chenjinsui.travel.dao.impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import xyz.chenjinsui.travel.dao.ICategoryDao;
import xyz.chenjinsui.travel.domain.Category;
import xyz.chenjinsui.travel.util.JDBCUtils;

import java.util.List;

public class CategoryDaoImpl implements ICategoryDao {

    private final JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<Category> findAll() {
        String sql = "select * from tab_category;";
        return template.query(sql, new BeanPropertyRowMapper<Category>(Category.class));
    }
}
