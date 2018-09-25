package api;

import entity.Product;

import java.io.IOException;
import java.util.List;

public interface ProductService
{
    List<Product> getAllProducts() throws IOException;

    Integer getProductCountOnList() throws IOException;

    boolean isProductOnWarehouse(String productName);

    boolean isProductExist(String productName);

    boolean isProductExist(Long productId);

    boolean saveProduct(Product product);

    Product getProductById(Long productId) throws IOException;

    Product getProductByName(String productName) throws IOException;
}
