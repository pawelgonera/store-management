package service;

import api.ProductFacade;
import entity.Product;
import exception.ProductCountNegativeException;
import exception.ProductNameEmptyException;
import exception.ProductPriceNoPositiveException;
import exception.ProductWeightNoPositiveException;

import java.util.Collections;
import java.util.List;

public class ProductFacadeImpl implements ProductFacade
{
    private static ProductFacadeImpl instance = null;
    private ProductServiceImpl productService = ProductServiceImpl.getInstance();

    public ProductFacadeImpl()
    {

    }

    public static ProductFacadeImpl getInstance()
    {
        if(instance == null)
            instance = new ProductFacadeImpl();

        return instance;
    }

    @Override
    public String createProduct(Product product)
    {
        try
        {
            productService.saveProduct(product);

        }catch (ProductPriceNoPositiveException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }catch (ProductWeightNoPositiveException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }catch (ProductCountNegativeException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }catch (ProductNameEmptyException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }

        return "Produkt został stworzony pomyślnie";
    }

    @Override
    public String removeProduct(String productName)
    {
        try
        {
             productService.removeProduct(productName);
        }catch (Exception e)
        {
            return e.getMessage();
        }

        return "Produkt został poprawnie usunięty";
    }

    @Override
    public List<Product> getAllProducts()
    {
        try
        {
            return productService.getAllProducts();

        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }
}
