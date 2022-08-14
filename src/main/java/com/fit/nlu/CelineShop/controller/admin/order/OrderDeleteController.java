package com.fit.nlu.CelineShop.controller.admin.order;


import com.fit.nlu.CelineShop.services.CartItemService;
import com.fit.nlu.CelineShop.services.impl.CartServiceItemImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/Admin/order/delete")
public class OrderDeleteController extends HttpServlet {
    CartItemService cartItemService = new CartServiceItemImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        cartItemService.delete(id);
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("account") != null) {
            response.sendRedirect(request.getContextPath() + "/Admin/order/list");
        } else {
            response.sendRedirect(request.getContextPath() + "/AdminLogin");
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

