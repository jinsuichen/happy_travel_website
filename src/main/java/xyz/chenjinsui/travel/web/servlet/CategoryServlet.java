package xyz.chenjinsui.travel.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import xyz.chenjinsui.travel.domain.Category;
import xyz.chenjinsui.travel.service.ICategoryService;
import xyz.chenjinsui.travel.service.impl.CategoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/category/*")
public class CategoryServlet extends BaseServlet {

    private final ICategoryService categoryService = new CategoryServiceImpl();

    /**
     * 查询所有
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> list = categoryService.findAll();

        writeValue(list, resp);
    }
}
