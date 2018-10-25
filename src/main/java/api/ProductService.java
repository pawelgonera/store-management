package api;

import entity.Product;
import exception.ProductCountNegativeException;
import exception.ProductNameEmptyException;
import exception.ProductPriceNoPositiveException;
import exception.ProductWeightNoPositiveException;

import java.util.List;

public interface ProductService
{
    List<Product> getAllProducts();

    Integer getProductCountOnList();

    boolean isProductOnWarehouse(String productName);

    boolean isProductExist(String productName);

    boolean isProductExist(Long productId);

    boolean saveProduct(Product product) throws ProductPriceNoPositiveException, ProductNameEmptyException, ProductCountNegativeException, ProductWeightNoPositiveException;

    Product getProductById(Long productId);

    Product getProductByName(String productName);

    void removeProduct(String productName);
}
