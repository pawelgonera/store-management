package validator;

import entity.Product;
import exception.ProductCountNegativeException;
import exception.ProductNameEmptyException;
import exception.ProductPriceNoPositiveException;
import exception.ProductWeightNoPositiveException;

public class ProductValidator
{
    private static ProductValidator instance = null;


    public ProductValidator()
    {

    }

    public static ProductValidator getInstance()
    {
        if(instance == null)
            instance = new ProductValidator();

        return instance;
    }

    public boolean isValidate(Product product) throws ProductPriceNoPositiveException, ProductCountNegativeException, ProductWeightNoPositiveException, ProductNameEmptyException
    {
        if(!isPriceNoNegative(product.getPrice()))
            throw new ProductPriceNoPositiveException("Price of " + product.getProductName() + " is negative!");

        if(!isProductCountNoNegative(product.getProductCount()))
            throw  new ProductCountNegativeException("Count of product " + product.getProductName() + " is negative!");

        if(!isWeightNoNegative(product.getWeight()))
            throw new ProductWeightNoPositiveException("Weight of " + product.getWeight() + " is neagative!");
        if(!isProductNameEmpty(product.getProductName()))
            throw new ProductNameEmptyException("Product " + product.getProductName() + " is empty!");

        return true;
    }

    private boolean isPriceNoNegative(float price)
    {
        if(price > 0)
            return true;
        else
            return false;
    }

    private boolean isProductCountNoNegative(int productCount)
    {
        if(productCount > 0)
            return true;
        else
            return false;
    }

    private boolean isWeightNoNegative(float weight)
    {
        if(weight > 0)
            return true;
        else
            return false;
    }

    private boolean isProductNameEmpty(String productName)
    {
        if(productName != null && !(productName.length() == 0))
            return true;
        else
            return false;
    }
}
