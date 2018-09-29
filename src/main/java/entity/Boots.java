package entity;

import entity.enums.Colors;
import entity.enums.ProductSeparators;
import entity.enums.SkinType;


public class Boots extends Product
{
    private int size;
    private SkinType skinType;

    public Boots(long id, String productName, float price, float weight, Colors color, int productCount, int size, SkinType skinType)
    {
        super(id, productName, price, weight, color, productCount);
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

    @Override
    public String toString()
    {
        return  ProductSeparators.BOOTS_ID.toString() + ProductSeparators.PRODUCT_SEPARATOR +
                standardToString() + ProductSeparators.PRODUCT_SEPARATOR+
                this.size + ProductSeparators.PRODUCT_SEPARATOR +
                this.skinType.toString();
    }
}
