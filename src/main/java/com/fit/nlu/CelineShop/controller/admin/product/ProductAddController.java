package com.fit.nlu.CelineShop.controller.admin.product;

import com.fit.nlu.CelineShop.model.Category;
import com.fit.nlu.CelineShop.model.Product;
import com.fit.nlu.CelineShop.model.User;
import com.fit.nlu.CelineShop.services.CategoryService;
import com.fit.nlu.CelineShop.services.ProductService;
import com.fit.nlu.CelineShop.services.impl.CategoryServiceImpl;
import com.fit.nlu.CelineShop.services.impl.ProductServiceImpl;
import com.fit.nlu.CelineShop.util.Constant;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(urlPatterns = {"/Admin/product/add"})
public class ProductAddController extends HttpServlet {
    ProductService productService = new ProductServiceImpl();
    CategoryService categoryService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("account") != null) {
            List<Category> categories = categoryService.getAll();
            request.setAttribute("categories", categories);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/view/admin/view/addProduct.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/AdminLogin");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String salePrice = request.getParameter("salePrice");
        String manufacturer = request.getParameter("manufacturer");
        String isLiked = request.getParameter("isLiked");
        String rating = request.getParameter("rating");
        String brand = request.getParameter("brand");
        String product_detail = request.getParameter("product-detail");
        String stock = request.getParameter("stock");
        String des = request.getParameter("des");
        int category = categoryService.get(request.getParameter("category")).getId();
        String image = request.getParameter("image");
        String user = request.getParameter("user");

        Product pro = new Product();
        pro.setName(name);//
        pro.setPrice(Long.parseLong(price));//
        pro.setSalePrice(Long.parseLong(salePrice));//
        pro.setManufacturer(manufacturer);//
        pro.setIsLiked(Integer.parseInt(isLiked));//
        pro.setRating(Integer.parseInt(rating));//
        pro.setBrand(brand);//
        pro.setProduct_detail(product_detail);//
        pro.setSoldQuantity(0);//
        pro.setStock(Integer.parseInt(stock));//
        pro.setDes(des);//
        pro.setCategory(categoryService.get(category));//
        pro.setImage(image);//
        pro.setCreatedBy(user);//
        pro.setCreatedDate(java.sql.Date.valueOf(LocalDate.now()));//
        productService.insert(pro);
        request.setAttribute("errorString", "Thêm sản phẩm thành công");
        response.sendRedirect(request.getContextPath() + "/Admin/product/list");
    }


}

