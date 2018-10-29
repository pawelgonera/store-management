package entity;

import entity.enums.ProductSeparators;
import entity.enums.SkinType;

public class Boots
{
    private long id;
    private int size;
    private SkinType skinType;
    private Product product;

    public Boots(Product product, int size, SkinType skinType)
    {
        this.product = product;
        this.size = size;
        this.skinType = skinType;
    }

    public Boots(long id, Product product, int size, SkinType skinType)
    {
        this.product = product;
        this.id = id;
        this.size = size;
        this.skinType = skinType;
    }

    public int getSize()
    {
        return size;
    }

    public SkinType isNaturalSkin()

    {
        return skinType;
    }

    public Product getProduct()
    {
        return product;
    }

    @Override
    public String toString()
    {
        return this.product.toString()
                + this.id + ProductSeparators.PRODUCT_SEPARATOR.getSeparator()
                + this.size + ProductSeparators.PRODUCT_SEPARATOR.getSeparator()
                + this.skinType.name();
    }
}
