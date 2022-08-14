package com.fit.nlu.CelineShop.controller.admin.category;


import com.fit.nlu.CelineShop.model.Category;
import com.fit.nlu.CelineShop.services.CategoryService;
import com.fit.nlu.CelineShop.services.impl.CategoryServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(urlPatterns = {"/Admin/category/edit"})
public class CategoryEditController extends HttpServlet {
    CategoryService cateService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Category category = cateService.get(Integer.parseInt(id));

        request.setAttribute("category", category);
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("account") != null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/view/admin/view/editCategory.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/AdminLogin");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Category category = new Category();
        category.setId(Integer.parseInt(request.getParameter("id")));
        category.setName(request.getParameter("name"));
        category.setUpdatedBy(request.getParameter("user"));
        category.setUpdatedDate(java.sql.Date.valueOf(LocalDate.now()));
        cateService.edit(category);

        response.sendRedirect(request.getContextPath() + "/Admin/category/list");

    }
}
