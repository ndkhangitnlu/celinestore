package com.fit.nlu.CelineShop.controller.admin.blog;

import com.fit.nlu.CelineShop.model.Blog;
import com.fit.nlu.CelineShop.services.BlogService;
import com.fit.nlu.CelineShop.services.impl.BlogServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

@WebServlet(name = "BlogEditController", value = "/Admin/news/edit")
public class BlogEditController extends HttpServlet {
    BlogService blogService = new BlogServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Blog blog = blogService.get(Integer.parseInt(id));
        request.setAttribute("blog", blog);
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("account") != null) {
            request.getRequestDispatcher("/view/admin/view/editBlog.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/AdminLogin");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        String name = request.getParameter("title");
        String des = request.getParameter("des");
        String image = request.getParameter("image");
        String category = request.getParameter("blog_cate");
        String date = request.getParameter("date");
        Blog blog = new Blog();
        blog.setId(Integer.parseInt(id));
        blog.setName(name);
        blog.setDes(des);
        blog.setImage(image);
        blog.setBlog_category(category);
        LocalDate date1 = LocalDate.parse(date);
        Date date2 = java.sql.Date.valueOf(date1);
        blog.setUpdatedBy(request.getParameter("user"));
        blog.setUpdatedDate(java.sql.Date.valueOf(LocalDate.now()));
        blog.setDate(date2);
        blogService.edit(blog);
        response.sendRedirect(request.getContextPath() + "/Admin/blog/list");
    }
}
