package entity;


import entity.enums.Colors;
import entity.enums.Material;
import entity.enums.ProductSeparators;

public class Cloth extends Product
{
    private int size;
    private Material material;

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
        return  ProductSeparators.CLOTH_ID.toString() + ProductSeparators.PRODUCT_SEPARATOR +
                standardToString() + ProductSeparators.PRODUCT_SEPARATOR +
                this.size + ProductSeparators.PRODUCT_SEPARATOR +
                this.material.toString();
    }
}