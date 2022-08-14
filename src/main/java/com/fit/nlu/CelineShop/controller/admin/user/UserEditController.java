package com.fit.nlu.CelineShop.controller.admin.user;


import com.fit.nlu.CelineShop.model.User;
import com.fit.nlu.CelineShop.services.UserService;
import com.fit.nlu.CelineShop.services.impl.UserServiceImpl;
import com.fit.nlu.CelineShop.util.SecurityUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(urlPatterns = {"/Admin/user/edit"})
public class UserEditController extends HttpServlet {
    UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User user = userService.get(id);
        request.setAttribute("user", user);
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("account") != null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/view/admin/view/editAccount.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/AdminLogin");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        user.setId(Integer.parseInt(request.getParameter("id")));
        user.setEmail(request.getParameter("email"));
        user.setPassword(SecurityUtils.hash(request.getParameter("password")));
        user.setName(request.getParameter("username"));
        user.setAvatar(request.getParameter("avatar"));
        user.setId(Integer.parseInt(request.getParameter("role")));
        user.setUpdatedBy(request.getParameter("user"));
        user.setUpdatedDate(java.sql.Date.valueOf(LocalDate.now()));
        userService.edit(user);
        response.sendRedirect(request.getContextPath() + "/Admin/user/list");
    }
}

