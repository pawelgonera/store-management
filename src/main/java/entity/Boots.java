package entity;

import entity.enums.Colors;
import entity.enums.ProductSeparators;
import entity.enums.SkinType;


public class Boots extends Product
{
    private int size;
    private SkinType skinType;

    public Boots(String productName, float price, float weight, Colors color, int productCount, int size, SkinType skinType)
    {
        super(productName, price, weight, color, productCount);
        this.size = size;
        this.skinType = skinType;
    }

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
        return  ProductSeparators.BOOTS_ID.getSeparator() + ProductSeparators.PRODUCT_SEPARATOR.getSeparator() +
                standardToString() + ProductSeparators.PRODUCT_SEPARATOR.getSeparator()+
                this.size + ProductSeparators.PRODUCT_SEPARATOR.getSeparator() +
                this.skinType.name();
    }
}
