package vn.edu.ntu.tuydung.controller;

import android.content.Context;

import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

import vn.edu.ntu.tuydung.model.AppDatabase;
import vn.edu.ntu.tuydung.model.DAOProduct;
import vn.edu.ntu.tuydung.model.Product;

public class CartControllerDB implements ICartcontroller
{
    AppDatabase database;
    ArrayList<Product> shoppingCart = new ArrayList<>();
    DAOProduct daoProduct;

    public CartControllerDB(Context context)
    {
        database = Room.databaseBuilder(context,AppDatabase.class,"appdp")
                .allowMainThreadQueries()
                .build();
        daoProduct = database.getProductDAO();
    }
    @Override
    public ArrayList<Product> listProduct() {
        return (ArrayList<Product>) daoProduct.getListProduct();
    }

    @Override
    public ArrayList<Product> listShopping() {
        return shoppingCart;
    }

    @Override
    public void addList(Product p) {
        daoProduct.insertProduct(p);
    }

    @Override
    public boolean addToShoppingCart(Product p) {

        if(shoppingCart.contains(p))
            return true;
        else{
            shoppingCart.add(p);
            return false;
        }
    }
}
