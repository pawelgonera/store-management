package entity;

import entity.enums.Colors;
import entity.enums.Material;
import entity.enums.ProductSeparators;

public class Cloth extends Product
{
    private int size;
    private Material material;

    public Cloth(String productName, float price, float weight, Colors color, int productCount, int size, Material material)
    {
        super(productName, price, weight, color, productCount);
        this.size = size;
        this.material = material;
    }

    public Cloth(long id, String productName, float price, float weight, Colors color, int productCount, int size, Material material)
    {
        super(id, productName, price, weight, color, productCount);
        this.size = size;
        this.material = material;
    }

    public int getSize()
    {
        return size;
    }

    public Material getMaterial()
    {
        return material;
    }

    @Override
    public String toString()
    {
        return  ProductSeparators.CLOTH_ID.getSeparator() + ProductSeparators.PRODUCT_SEPARATOR.getSeparator() +
                standardToString() + ProductSeparators.PRODUCT_SEPARATOR.getSeparator() +
                this.size + ProductSeparators.PRODUCT_SEPARATOR.getSeparator() +
                this.material.name();
    }
}