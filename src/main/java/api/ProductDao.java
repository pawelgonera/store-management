package api;

import entity.Product;
import java.util.List;

public interface ProductDao
{
    void createProduct(Product product);

    void updateProduct(Product product);

    void deleteProductById(Long productId);

    void deleteProductByName(String productName);

    List<Product> getAllProducts();

    Product getProductById(Long productId);

}
