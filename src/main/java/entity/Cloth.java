package entity;

import entity.enums.Material;
import entity.enums.ProductSeparators;

public class Cloth
{
    private long id;
    private int size;
    private Material material;
    private Product product;

    public Cloth(Product product, int size, Material material)
    {
        this.product = product;
        this.size = size;
        this.material = material;
    }

    public Cloth(long id, Product product, int size, Material material)
    {
        this.id = id;
        this.product = product;
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

    public Product getProduct()
    {
        return product;
    }

    @Override
    public String toString()
    {
        return this.product.toString() + this.id
                + ProductSeparators.PRODUCT_SEPARATOR.getSeparator()
                + this.size + ProductSeparators.PRODUCT_SEPARATOR.getSeparator()
                + this.material.name();
    }


}