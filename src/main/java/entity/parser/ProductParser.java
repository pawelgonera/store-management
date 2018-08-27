package entity.parser;

import entity.Boots;
import entity.Cloth;
import entity.Product;

public class ProductParser
{
    public static Product convertProduct(String strProduct, String productType)
    {
        if(productType.equals("PRODUCT"))
        {
            return parseProduct(strProduct);
        }
        else if(productType.equals(("BOOTS")))
        {
            return parseBoots(strProduct);
        }
        else if(productType.equals("CLOTH"))
        {
            return parseCloth(strProduct);
        }

        return null;
    }

    public static Product parseBoots(String strProduct)
    {
        String[] productInfos = strProduct.split("#");

        Long id = Long.parseLong(productInfos[0]);
        String productName = productInfos[1];
        Float price = Float.parseFloat(productInfos[2]);
        Float weight = Float.parseFloat(productInfos[3]);
        String color = productInfos[4];
        Integer productCount = Integer.parseInt(productInfos[5]);
        Integer size = Integer.parseInt(productInfos[6]);
        Boolean isNaturalSkin = Boolean.parseBoolean(productInfos[7]);

        return new Boots(id, productName, price, weight, color, productCount, size, isNaturalSkin);
    }

    private static Product parseCloth(String strProduct)
    {
        String[] productInfos = strProduct.split("#");

        Long id = Long.parseLong(productInfos[0]);
        String productName = productInfos[1];
        Float price = Float.parseFloat(productInfos[2]);
        Float weight = Float.parseFloat(productInfos[3]);
        String color = productInfos[4];
        Integer productCount = Integer.parseInt(productInfos[5]);
        String size = productInfos[6];
        String material = productInfos[7];

        return new Cloth(id, productName, price, weight, color, productCount, size, material);
    }

    private static Product parseProduct(String strProduct)
    {
        String[] productInfos = strProduct.split("-");

        Long id = Long.parseLong(productInfos[0]);
        String productName = productInfos[1];
        Float price = Float.parseFloat(productInfos[2]);
        Float weight = Float.parseFloat(productInfos[3]);
        String color = productInfos[4];
        Integer productCount = Integer.parseInt(productInfos[5]);

        return new Product(id, productName, price, weight, color, productCount);
    }
}
