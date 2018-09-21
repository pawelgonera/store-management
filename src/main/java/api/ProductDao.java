package api;

import entity.Product;
import exception.ProductCountNegativeException;
import exception.ProductNameEmptyException;
import exception.ProductPriceNoPositiveException;
import exception.ProductWeightNoPositiveException;

import java.io.IOException;
import java.util.List;

public interface ProductDao
{
    void saveProduct(Product product) throws IOException, ProductPriceNoPositiveException, ProductNameEmptyException, ProductCountNegativeException, ProductWeightNoPositiveException;

    void saveProducts(List<Product> products) throws IOException;

    void removeProductById(Long productId) throws IOException;

    void removeProductByName(String productName) throws IOException;;

    List<Product> getAllProducts() throws IOException;

    Product getProductById(Long productId) throws IOException;

    Product getProductByName(String productName) throws IOException;
}
