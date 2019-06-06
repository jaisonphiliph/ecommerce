package model;

import java.util.List;

public class Product {

    private String brandName;

    private String type;

    public Product(String brandName, String type){
        this.brandName = brandName;
        this.type = type;
    }

    public Product(){
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
