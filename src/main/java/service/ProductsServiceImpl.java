package service;

import model.Product;
import java.util.ArrayList;
import java.util.List;

public class ProductsServiceImpl {

    private static List<Product> productList;

    public List<Product> getProducts() {
        return getProductList();
    }

    public static List<Product> getProductList() {
        if(productList == null) {
            productList = new ArrayList<Product>();
        }
        return productList;
    }

    public static void setProductList(List<Product> productList) {
        ProductsServiceImpl.productList = productList;
    }

    public void createProduct(Product p1) {
        getProductList().add(p1);
    }


}