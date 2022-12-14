package com.fit.nlu.CelineShop.dao.impl;


import com.fit.nlu.CelineShop.dao.CartDao;
import com.fit.nlu.CelineShop.jdbc.JDBCConnection;
import com.fit.nlu.CelineShop.model.Cart;
import com.fit.nlu.CelineShop.model.User;
import com.fit.nlu.CelineShop.services.UserService;
import com.fit.nlu.CelineShop.services.impl.UserServiceImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CartDaoImpl extends JDBCConnection implements CartDao {
    UserService userS = new UserServiceImpl();

    @Override
    public void edit(Cart cart) {
        String sql = "UPDATE cart SET u_id = ?, buyDate = ? WHERE id = ?";
        Connection con = getJDBCConnection();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, cart.getBuyer().getId());
            ps.setDate(2, new Date(cart.getBuyDate().getTime()));
            ps.setString(3, cart.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void insert(Cart cart) {
        String sql = "INSERT INTO Cart(id,u_id, buyDate) VALUES (?,?,?)";
        Connection con = getJDBCConnection();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cart.getId());
            ps.setInt(2, cart.getBuyer().getId());
            ps.setDate(3, new Date(cart.getBuyDate().getTime()));
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM cart WHERE id = ?";
        Connection con = getJDBCConnection();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public Cart get(String name) {
        return null;
    }

    @Override
    public List<Cart> getAll() {
        List<Cart> cartList = new ArrayList<Cart>();
        String sql = "SELECT cart.id, cart.buyDate, u_id "
                + "FROM cart ";
        Connection con = getJDBCConnection();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User user = userS.get(rs.getInt("u_id"));

                Cart cart = new Cart();
                cart.setId(rs.getString("id"));
                cart.setBuyDate(rs.getDate("buyDate"));
                cart.setBuyer(user);

                cartList.add(cart);

            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return cartList;
    }

    public List<Cart> search(String name) {
        List<Cart> cartList = new ArrayList<Cart>();
        String sql = "SELECT cart.id, cart.buyDate, User.email, user.username, user.id AS user_id "
                + "FROM cart INNER JOIN user " + "ON cart.u_id = user.id LIKE User.email = ?";
        Connection con = getJDBCConnection();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User user = userS.get(rs.getInt("user_id"));

                Cart cart = new Cart();
                cart.setId(rs.getString("id"));
                cart.setBuyDate(rs.getDate("buyDate"));
                cart.setBuyer(user);

                cartList.add(cart);

            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return cartList;
    }

    @Override
    public Cart get(int id) {
        String sql = "SELECT cart.id, cart.buyDate, User.email, user.username, user.id AS user_id "
                + "FROM cart INNER JOIN user " + "ON cart.u_id = user.id WHERE cart.id=?";
        Connection con = getJDBCConnection();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User user = userS.get(rs.getInt("user_id"));

                Cart cart = new Cart();
                cart.setId(rs.getString("id"));
                cart.setBuyDate(rs.getDate("buyDate"));
                cart.setBuyer(user);

                return cart;

            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

}

