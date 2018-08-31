package api;

import entity.Product;

import java.io.IOException;
import java.util.List;

public interface ProductService
{
    List<Product> getAllProducts() throws IOException;

    Integer getProductCountOnList() throws IOException;

    Product getProductByName(String productName) throws IOException;

    boolean isProductOnWarehouse(String productName) throws IOException;

    boolean isProductExistByName(String productName) throws IOException;

    boolean isProductExistById(Long id) throws IOException;
}
