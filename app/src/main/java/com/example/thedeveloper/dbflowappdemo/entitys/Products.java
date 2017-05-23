package com.example.thedeveloper.dbflowappdemo.entitys;

import com.example.thedeveloper.dbflowappdemo.dbconfig.DbConfig;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;


@Table(database = DbConfig.class)
public class Products extends BaseModel {

    @Column
    @PrimaryKey(autoincrement = true)
    private Long id;
    @Column
    private String productName;
    @Column
    private double productPrice;

    public Products() {
    }

    public Products(String productName, double productPrice) {
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
