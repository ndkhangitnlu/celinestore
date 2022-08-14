package com.fit.nlu.CelineShop.controller.admin.order;


import com.fit.nlu.CelineShop.model.CartItem;
import com.fit.nlu.CelineShop.services.CartItemService;
import com.fit.nlu.CelineShop.services.impl.CartServiceItemImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "OrderEditController", value = "/Admin/order/edit")
public class OrderEditController extends HttpServlet {
    CartItemService cartItemService = new CartServiceItemImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        CartItem cartItem = cartItemService.get(id);
        request.setAttribute("id", id);
        request.setAttribute("cartItem", cartItem);
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("account") != null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/view/admin/view/editOrder.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/AdminLogin");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        String quantity = request.getParameter("quantity");
        String salePrice = request.getParameter("salePrice");
        String size = request.getParameter("size");
        String status = request.getParameter("status");
        CartItem cartItem = cartItemService.get(id);
        cartItem.setQuantity(Integer.parseInt(quantity));
        cartItem.setUnitPrice(Integer.parseInt(salePrice));
        cartItem.setSize(size);
        cartItem.setStatus(status);
        cartItemService.edit(cartItem);
        response.sendRedirect(request.getContextPath() + "/Admin/order/list");
    }
}
