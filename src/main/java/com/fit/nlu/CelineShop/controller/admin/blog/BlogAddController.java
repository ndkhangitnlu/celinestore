package com.fit.nlu.CelineShop.controller.admin.blog;


import com.fit.nlu.CelineShop.model.Blog;
import com.fit.nlu.CelineShop.model.Category;
import com.fit.nlu.CelineShop.services.BlogService;
import com.fit.nlu.CelineShop.services.impl.BlogServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@WebServlet(name = "BlogAddController", value = "/Admin/blog/add")
public class BlogAddController extends HttpServlet {
    BlogService blogService = new BlogServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("account") != null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/view/admin/view/addBlog.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/AdminLogin");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("title");
        String des = request.getParameter("des");
        String image = request.getParameter("image");
        String category = request.getParameter("blog_cate");
        String date = request.getParameter("date");
        Blog blog = new Blog();
        blog.setName(name);
        blog.setDes(des);
        blog.setImage(image);
        blog.setBlog_category(category);
        LocalDate date1 = LocalDate.parse(date);
        Date date2 = java.sql.Date.valueOf(date1);
        blog.setDate(date2);
        blog.setCreatedBy(request.getParameter("user"));
        blog.setCreatedDate(java.sql.Date.valueOf(LocalDate.now()));
        blogService.insert(blog);
        response.sendRedirect(request.getContextPath() + "/Admin/blog/list");
    }
}
