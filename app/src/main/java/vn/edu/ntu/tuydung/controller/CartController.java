package vn.edu.ntu.tuydung.controller;

import android.app.Application;

import java.util.ArrayList;

import vn.edu.ntu.tuydung.model.Product;

public class CartController extends Application implements ICartcontroller{
    ArrayList<Product> listProduct = new ArrayList<>();
    ArrayList<Product> listShoppingCart=new ArrayList<>();

    public CartController()
    {
        listProduct.add(new Product("Xoài","Hòa Lộc", 60000));
        listProduct.add(new Product("Ổi","Da Trơn", 20000));
        listProduct.add(new Product("Thanh long","Không Hạt", 45000));
        listProduct.add(new Product("Mận","Sửa", 30000));
        listProduct.add(new Product("Bưởi","Năm Roi", 37000));
        listProduct.add(new Product("Sầu riêng","Khánh Sơn", 70000));
        listProduct.add(new Product(" Cam","Sành", 50000));
        listProduct.add(new Product("Măng cục","Ngon", 39000));
    }

    @Override
    public ArrayList<Product> listProduct() {
        return listProduct;
    }

    @Override
    public ArrayList<Product> listShopping() {
        return listShoppingCart;
    }

    @Override
    public void addList(Product p) {

    }

    @Override
    public boolean addToShoppingCart(Product p)
    {
        if (!listShoppingCart.contains(p))
        {
            listShoppingCart.add(p);
            return true;
        }
        return false;
    }
}
