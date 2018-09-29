package entity.parser;

import entity.Boots;
import entity.Cloth;
import entity.Product;
import entity.enums.Colors;
import entity.enums.Material;
import entity.enums.ProductSeparators;
import entity.enums.SkinType;

import static entity.parser.ColorParser.getColor;
import static entity.parser.MaterialParser.getMaterial;
import static entity.parser.SkinTypeParser.getSkinType;


public class ProductParser
{
    private static final String SEPARATOR = ProductSeparators.PRODUCT_SEPARATOR.getSeparator();
    private static final String PRODUCT_ID = ProductSeparators.PRODUCT_ID.getSeparator();
    private static final String CLOTH_ID = ProductSeparators.CLOTH_ID.getSeparator();
    private static final String BOOTS_SEPARATOR = ProductSeparators.BOOTS_ID.getSeparator();

    public static Product convertProduct(String strProduct)
    {

        char productType = strProduct.charAt(0);

        if(productType == PRODUCT_ID.charAt(0))
        {
            return parseProduct(strProduct);
        }
        else if(productType == CLOTH_ID.charAt(0))
        {
            return parseBoots(strProduct);
        }
        else if(productType == BOOTS_SEPARATOR.charAt(0))
        {
            return parseCloth(strProduct);
        }

        return null;
    }

    private static Product parseProduct(String strProduct)
    {
        String[] productInfos = strProduct.split(SEPARATOR);

        Long id = Long.parseLong(productInfos[1]);
        String productName = productInfos[2];
        Float price = Float.parseFloat(productInfos[3]);
        Float weight = Float.parseFloat(productInfos[4]);
        String color = productInfos[5];
        Colors colors = getColor(color);
        Integer productCount = Integer.parseInt(productInfos[6]);

        return new Product(id, productName, price, weight, colors, productCount);
    }

    private static Product parseCloth(String strProduct)
    {
        String[] productInfos = strProduct.split(SEPARATOR);


        Long id = Long.parseLong(productInfos[1]);
        String productName = productInfos[2];
        Float price = Float.parseFloat(productInfos[3]);
        Float weight = Float.parseFloat(productInfos[4]);
        String color = productInfos[5];
        Colors colors = getColor(color);
        Integer productCount = Integer.parseInt(productInfos[6]);
        Integer size = Integer.parseInt(productInfos[7]);
        String material = productInfos[8];
        Material materials = getMaterial(material);

        return new Cloth(id, productName, price, weight, colors, productCount, size, materials);
    }

    private static Product parseBoots(String strProduct)
    {
        String[] productInfos = strProduct.split(SEPARATOR);

        Long id = Long.parseLong(productInfos[1]);
        String productName = productInfos[2];
        Float price = Float.parseFloat(productInfos[3]);
        Float weight = Float.parseFloat(productInfos[4]);
        String color = productInfos[5];
        Colors colors = getColor(color);
        Integer productCount = Integer.parseInt(productInfos[6]);
        Integer size = Integer.parseInt(productInfos[7]);
        String skinType = productInfos[8];
        SkinType skinTypes = getSkinType(skinType);

        return new Boots(id, productName, price, weight, colors, productCount, size, skinTypes);
    }

}
