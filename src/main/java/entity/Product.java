package entity;

import entity.enums.Colors;
import entity.enums.ProductSeparators;

public class Product
{
    private long id;
    private String productName;
    private float price;
    private float weight;
    private Colors color;
    private int productCount;

    public Product(String productName, float price, float weight, Colors color, int productCount)
    {
        this.productName = productName;
        this.price = price;
        this.weight = weight;
        this.color = color;
        this.productCount = productCount;
    }

    public Product(long id, String productName, float price, float weight, Colors color, int productCount)
    {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.weight = weight;
        this.color = color;
        this.productCount = productCount;
    }

    public long getId()
    {
        return id;
    }

    public String getProductName()
    {
        return productName;
    }

    public float getPrice()
    {
        return price;
    }

    public float getWeight()
    {
        return weight;
    }

    public Colors getColor()
    {
        return color;
    }

    public int getProductCount()
    {
        return productCount;
    }

    public void setPrice(float price)
    {
        this.price = price;
    }

    public void setProductCount(int productCount)
    {
        this.productCount = productCount;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;

        hash = 31 * hash + (int) id;
        hash = 31 * hash + (productName == null ? 0 : productName.hashCode());
        hash = 31 * hash + (int) price;
        hash = 31 * hash + (int) weight;
        hash = 31 * hash + (color == null ? 0 : color.hashCode());
        hash = 31 * hash + productCount;

        return hash;
    }

    @Override
    public String toString()
    {
        return this.id + ProductSeparators.PRODUCT_SEPARATOR.getSeparator() +
                this.productName + ProductSeparators.PRODUCT_SEPARATOR.getSeparator() +
                this.price + ProductSeparators.PRODUCT_SEPARATOR.getSeparator() +
                this.weight + ProductSeparators.PRODUCT_SEPARATOR.getSeparator() +
                this.color + ProductSeparators.PRODUCT_SEPARATOR.getSeparator() +
                this.productCount + ProductSeparators.PRODUCT_SEPARATOR.getSeparator();
    }
}