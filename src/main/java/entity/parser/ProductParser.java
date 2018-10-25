package entity.parser;

import entity.Boots;
import entity.Cloth;
import entity.Product;
import entity.enums.Colors;
import entity.enums.Material;
import entity.enums.ProductSeparators;
import entity.enums.SkinType;

import java.util.Vector;

import static entity.parser.ColorParser.getColor;
import static entity.parser.MaterialParser.getMaterial;
import static entity.parser.SkinTypeParser.getSkinType;


public class ProductParser
{
    private static final String PRODUCT_ID = ProductSeparators.PRODUCT_ID.getSeparator();
    private static final String CLOTH_ID = ProductSeparators.CLOTH_ID.getSeparator();
    private static final String BOOTS_ID = ProductSeparators.BOOTS_ID.getSeparator();

    public static Product convertProduct(Vector strProduct)
    {
        String productType = strProduct.firstElement().toString();

        if(productType.equals(PRODUCT_ID))
        {
            return parseProduct(strProduct);
        }
        else if(productType.equals(CLOTH_ID))
        {
            return parseCloth(strProduct);
        }
        else if(productType.equals(BOOTS_ID))
        {
            return parseBoots(strProduct);
        }

        return null;
    }

    private static Product parseProduct(Vector strProduct)
    {
        Object[] productInfos = strProduct.toArray();

        Long id = Long.parseLong(productInfos[1].toString());
        String productName = (String) productInfos[2];
        Float price = Float.parseFloat(productInfos[3].toString());
        Float weight = Float.parseFloat(productInfos[4].toString());
        String color = (String) productInfos[5];
        Colors colors = getColor(color);
        Integer productCount = Integer.parseInt(productInfos[6].toString());

        return new Product(id, productName, price, weight, colors, productCount);
    }

    private static Product parseCloth(Vector strProduct)
    {
        Object[] productInfos = strProduct.toArray();

        Long id = Long.parseLong(productInfos[1].toString());
        String productName = (String) productInfos[2];
        Float price = Float.parseFloat(productInfos[3].toString());
        Float weight = Float.parseFloat(productInfos[4].toString());
        String color = (String) productInfos[5];
        Colors colors = getColor(color);
        Integer productCount = Integer.parseInt(productInfos[6].toString());
        Integer size = Integer.parseInt(productInfos[7].toString());
        String material = (String) productInfos[8];
        Material materials = getMaterial(material);

        return new Cloth(id, productName, price, weight, colors, productCount, size, materials);
    }

    private static Product parseBoots(Vector strProduct)
    {
        Object[] productInfos = strProduct.toArray();

        Long id = Long.parseLong(productInfos[1].toString());
        String productName = (String) productInfos[2];
        Float price = Float.parseFloat(productInfos[3].toString());
        Float weight = Float.parseFloat(productInfos[4].toString());
        String color = (String) productInfos[5];
        Colors colors = getColor(color);
        Integer productCount = Integer.parseInt(productInfos[6].toString());
        Integer size = Integer.parseInt(productInfos[7].toString());
        String skinType = (String) productInfos[9];
        SkinType skinTypes = getSkinType(skinType);

        return new Boots(id, productName, price, weight, colors, productCount, size, skinTypes);
    }

}
