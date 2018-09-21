package entity;

import entity.Product;

public class Cloth extends Product
{
    private static final String PRODUCT_TYPE = "C";
    private String size;
    private String material;

    public Cloth(long id, String productName, float price, float weight, String color, int productCount, String size, String material)
    {
        super(id, productName, price, weight, color, productCount);
        this.size = size;
        this.material = material;
    }

    public String getSize()
    {
        return size;
    }

    public String getMaterial()
    {
        return material;
    }

    @Override
    public String toString()
    {
        return  PRODUCT_TYPE + "-" +
                standardToString() + "-" +
                this.size + "-" +
                this.material;
    }
}