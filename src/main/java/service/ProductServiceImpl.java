package service;

import api.ProductDao;
import api.ProductService;
import dao.ProductDaoImpl;
import entity.Product;
import validator.ProductValidator;
import java.util.List;

public class ProductServiceImpl implements ProductService
{
    private static ProductServiceImpl instance = null;

    private ProductDao productDao = ProductDaoImpl.getInstance();

    private ProductValidator productValidator = ProductValidator.getInstance();

    public ProductServiceImpl()
    {

    }

    public static ProductServiceImpl getInstance()
    {
        if(instance == null)
            instance = new ProductServiceImpl();

        return instance;
    }

    @Override
    public List<Product> getAllProducts()
    {
        return productDao.getAllProducts();
    }

    @Override
    public Integer getProductCountOnList()
    {
        return productDao.getAllProducts().size();
    }

    @Override
    public boolean isProductOnWarehouse(String productName)
    {
        List<Product> products;
        products = productDao.getAllProducts();

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
    public boolean isProductExist(String productName)
    {
        Product product;
        product = getProductByName(productName);

        if(product == null)
                return false;

        return true;
    }

    @Override
    public boolean isProductExist(Long id)
    {
        Product product;
        product = getProductById(id);

        if(product == null)
                return false;

        return true;
    }

    @Override
    public boolean saveProduct(Product product)
    {
        try
        {
            if(productValidator.isValidate(product))
            {
                productDao.createProduct(product);
                return  true;
            }

        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }

        return  false;
    }

    @Override
    public Product getProductById(Long productId)
    {
        List<Product> products = getAllProducts();

        for(Product product : products)
        {
            if(product.getId() == productId)
                return product;
        }

        return null;
    }

    @Override
    public Product getProductByName(String productName)
    {
        List<Product> products = getAllProducts();

        for(Product product : products)
        {
            if(product.getProductName().equals(productName))
                return product;
        }

        return null;
    }

    @Override
    public void removeProduct(String productName)
    {
        productDao.deleteProductByName(productName);
    }
}
