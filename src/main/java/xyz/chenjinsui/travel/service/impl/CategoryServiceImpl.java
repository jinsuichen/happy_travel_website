package xyz.chenjinsui.travel.service.impl;

import xyz.chenjinsui.travel.dao.ICategoryDao;
import xyz.chenjinsui.travel.dao.impl.CategoryDaoImpl;
import xyz.chenjinsui.travel.domain.Category;
import xyz.chenjinsui.travel.service.ICategoryService;

import java.util.List;

public class CategoryServiceImpl implements ICategoryService {

    private final ICategoryDao categoryDao  = new CategoryDaoImpl();

    @Override
    public List<Category> findAll() {
        return categoryDao.findAll();
    }
}
