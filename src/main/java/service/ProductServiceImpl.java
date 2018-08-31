package service;

import api.ProductDao;
import api.ProductService;
import dao.ProductDaoImpl;
import entity.Product;
import java.io.IOException;
import java.util.List;

public class ProductServiceImpl implements ProductService
{
    private static ProductServiceImpl instance = null;

    ProductDao productDao = new ProductDaoImpl("products", "PRODUCT");

    public ProductServiceImpl() throws IOException
    {

    }

    public static ProductServiceImpl getInstance() throws IOException
    {
        if(instance == null)
            instance = new ProductServiceImpl();

        return instance;
    }

    @Override
    public List<Product> getAllProducts() throws IOException
    {
        List<Product> products = productDao.getAllProducts();

        return products;
    }

    @Override
    public Integer getProductCountOnList() throws IOException
    {
        int count = productDao.getAllProducts().size();
        return count;
    }

    @Override
    public Product getProductByName(String productName) throws IOException
    {
        Product product = productDao.getProductByName(productName);

            if(product.getProductName().equals(productName))
                return product;


        return null;
    }

    @Override
    public boolean isProductOnWarehouse(String productName) throws IOException
    {
        List<Product> products = productDao.getAllProducts();

        for(int i = 0; i<products.size(); i++)
        {
            Product product = products.get(i);
            if(product.getProductName().equals(productName) && product.getProductCount() > 0)
            {
                    return true;
            }
        }

        return false;
    }

    @Override
    public boolean isProductExistByName(String productName) throws IOException
    {
        Product product = productDao.getProductByName(productName);

            if(product == null)
                return false;

        return true;
    }

    @Override
    public boolean isProductExistById(Long id) throws IOException
    {
        Product product = productDao.getProductById(id);

            if(product == null)
                return false;

        return true;
    }
}
