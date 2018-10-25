package api;

import entity.Product;
import java.util.List;

public interface ProductService
{
    List<Product> getAllProducts();

    Integer getProductCountOnList();

    boolean isProductOnWarehouse(String productName);

    boolean isProductExist(String productName);

    boolean isProductExist(Long productId);

    boolean saveProduct(Product product);

    Product getProductById(Long productId);

    Product getProductByName(String productName);

    void removeProduct(String productName);
}
