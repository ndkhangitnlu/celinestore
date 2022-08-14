package com.fit.nlu.CelineShop.controller.client.product;


import com.fit.nlu.CelineShop.model.Product;
import com.fit.nlu.CelineShop.services.ProductService;
import com.fit.nlu.CelineShop.services.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = "/product/searchByName")
public class ProductSearchByNameAjax extends HttpServlet {
    ProductService productService = new ProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        int currentPage = 1;
        int productsPerPage = 12;
        if (request.getParameter("page") != null)
            currentPage = Integer.parseInt(
                    request.getParameter("page"));
        final String name = request.getParameter("name");
        List<Product> productSearchByName = productService.searchByName(name, currentPage, productsPerPage);
        int numOfProduct = productSearchByName.size();
        int numOfPages = numOfProduct / productsPerPage;
        if (numOfPages % productsPerPage > 0) {
            numOfPages++;
        }
        PrintWriter out = response.getWriter();
        for (Product p : productSearchByName) {
            out.println(
                    "                        <div class=\"col-sm-4\">\n" +
                            "                            <div class=\"product-image-wrapper\">\n" +
                            "                                <a href=\"https://celinestore.azurewebsites.net/product/detail?id=" + p.getId() + "\">\n" +
                            "                                <div class=\"single-products\">\n" +
                            "                                    <div class=\"productinfo text-center\">\n" +
                            "                                        <div class=\"product-img\">\n" +
                            "                                                <img src=\"" + p.getImage() + "\" alt=\"\" id=\"img__sp\"/></div>\n" +
                            "                                        <h4 class=\"home-product-item__name\">" + p.getName() + "</h4>\n" +
                            "                                    </div>\n" +
                            "                                    <div class=\"home-product-item__price\">\n" +
                            "                                        <span class=\"home-product-item__price-old\">" + p.getPrice() + "đ</span>\n" +
                            "                                        <span class=\"home-product-item__price-current\">" + p.getSalePrice() + "đ</span>\n" +
                            "                                    </div>\n" +
                            "                                    <div class=\"home-product-item__action\">\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t<span class=\"home-product-item__like home-product-item__like--liked\">\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t\t<i class=\"home-product-item__like-icon-empty far fa-heart\"></i>\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t\t<i class=\"home-product-item__like-icon-fill fas fa-heart\"></i>\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t</span>\n" +
                            "                                        <div class=\"home-product-item__rating\">\n");
            for (int i = 0; i < p.getRating(); i++) {
                out.println("<i class=\"home-product-item__star--gold fas fa-star\"></i>");
            }
            out.println(
                    "                                        </div>\n" +
                            "                                        <span class=\"home-product-item__sold\">" + p.getSoldQuantity() + "đã bán</span>\n" +
                            "                                    </div>\n" +
                            "                                    <div class=\"home-product-item__origin\">\n" +
                            "                                        <span class=\"home-product-item__brand\">" + p.getBrand() + "</span>\n" +
                            "                                        <span class=\"home-product-item__origin-name\">" + p.getManufacturer() + "</span>\n" +
                            "                                    </div>\n" +
                            "                                </div>\n" +
                            "                                </a>\n" +
                            "                            </div>\n" +
                            "                        </div>");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
