package com.fit.nlu.CelineShop.controller.admin;

import com.fit.nlu.CelineShop.model.*;
import com.fit.nlu.CelineShop.services.*;
import com.fit.nlu.CelineShop.services.impl.*;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "AdminWelcome", value = "/Admin/Welcome")
public class AdminWelcome extends HttpServlet {
    CartItemService cartItemService = new CartServiceItemImpl();
    UserService userService = new UserServiceImpl();
    CartService cartService = new CartServiceImpl();
    ProductService productService = new ProductServiceImpl();
    CategoryService categoryService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<CartItem> items = cartItemService.getAll();
        Long total = 0L;
        double cost = 0L;
        request.setAttribute("items", items.size());
        List<User> users = userService.getAll();
        request.setAttribute("users", users.size());
        List<Cart> carts = cartService.getAll();
        request.setAttribute("carts", carts.size());
        for (CartItem item : items) {
            total += item.getUnitPrice() * item.getQuantity();
        }
        cost = total * 0.4;// chi phí bỏ ra 40%
        request.setAttribute("cost", cost);
        request.setAttribute("profit", total - cost);
        request.setAttribute("total", total);
        request.setAttribute("activeUsers", ActiveUserCount.getActiveSessionNumber());

        Gson gsonObj = new Gson();
        Map<Object, Object> map = null;
        List<Map<Object, Object>> list = new ArrayList<Map<Object, Object>>();
        for (Category category : categoryService.getAll()) {
            map = new HashMap<Object, Object>();
            map.put("label", category.getName());
            map.put("y", productService.searchByCategory(category.getId()).size());
            list.add(map);


        }

        String dataPoints = gsonObj.toJson(list);
        request.setAttribute("dataPoints", dataPoints);
        request.getRequestDispatcher("/view/admin/view/homePage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
