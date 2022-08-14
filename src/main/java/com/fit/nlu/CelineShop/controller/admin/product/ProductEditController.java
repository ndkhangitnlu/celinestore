package com.fit.nlu.CelineShop.controller.admin.product;

import com.fit.nlu.CelineShop.model.Category;
import com.fit.nlu.CelineShop.model.Product;
import com.fit.nlu.CelineShop.services.CategoryService;
import com.fit.nlu.CelineShop.services.ProductService;
import com.fit.nlu.CelineShop.services.impl.CategoryServiceImpl;
import com.fit.nlu.CelineShop.services.impl.ProductServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(urlPatterns = {"/Admin/product/edit"})
public class ProductEditController extends HttpServlet {
    ProductService productService = new ProductServiceImpl();
    CategoryService categoryService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Product product = productService.get(Integer.parseInt(id));
        List<Category> categories = categoryService.getAll();

        request.setAttribute("categories", categories);

        request.setAttribute("product", product);
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("account") != null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/view/admin/view/editProduct.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/AdminLogin");
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String name = request.getParameter("name");//
        String price = request.getParameter("price");//
        String salePrice = request.getParameter("salePrice");//
        String manufacturer = request.getParameter("manufacturer");//
        String isLiked = request.getParameter("isLiked");//
        String rating = request.getParameter("rating");//
        String brand = request.getParameter("brand");//
        String product_detail = request.getParameter("product_detail");//
        String stock = request.getParameter("stock");//
        String des = request.getParameter("des");//
        String soldQuantity = request.getParameter("soldQuantity");//
        int category = Integer.parseInt(request.getParameter("category"));
        String image = request.getParameter("image");//
        String user = request.getParameter("user");//
        Product product = new Product();
        product.setId(Integer.parseInt(request.getParameter("id")));
        product.setName(name);
        product.setPrice(Long.parseLong(price));
        product.setSalePrice(Long.parseLong(salePrice));
        product.setManufacturer(manufacturer);
        product.setIsLiked(Integer.parseInt(isLiked));
        product.setRating(Integer.parseInt(rating));
        product.setBrand(brand);
        product.setProduct_detail(product_detail);
        product.setStock(Integer.parseInt(stock));
        product.setDes(des);
        product.setSoldQuantity(Integer.parseInt(soldQuantity));
        product.setCategory(categoryService.get(category));
        product.setImage(image);
        product.setUpdatedBy(user);
        product.setUpdatedDate(java.sql.Date.valueOf(LocalDate.now()));
        productService.edit(product);
        response.sendRedirect(request.getContextPath() + "/Admin/product/list");
    }
}

