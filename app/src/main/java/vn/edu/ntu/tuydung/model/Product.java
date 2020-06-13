package vn.edu.ntu.tuydung.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="Product")

public class Product {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    long id;
    @NonNull
    String name;
    @NonNull
    int price;

    String desc;

    public Product() {
    }

    public Product(String name, String desc, int price) {
        this.name = name;
        this.desc = desc;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
