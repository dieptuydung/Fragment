package vn.edu.ntu.tuydung.controller;

import android.app.Application;

import java.util.ArrayList;

import vn.edu.ntu.tuydung.model.Product;

public interface ICartcontroller {
    public ArrayList<Product> listProduct();

    public ArrayList<Product> listShopping();

    public boolean addToShoppingCart(Product p);
}
