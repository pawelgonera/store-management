package entity.parser;

import entity.Boots;
import entity.Cloth;
import entity.Product;

public class ProductParser
{
    public static Product convertProduct(String strProduct)
    {
        char productType = strProduct.charAt(0);

        if(productType == 'P')
        {
            return parseProduct(strProduct);
        }
        else if(productType == 'B')
        {
            return parseBoots(strProduct);
        }
        else if(productType == 'C')
        {
            return parseCloth(strProduct);
        }

        return null;
    }

    public static Product parseBoots(String strProduct)
    {
        String[] productInfos = strProduct.split("-");

        String productType = productInfos[0];
        Long id = Long.parseLong(productInfos[1]);
        String productName = productInfos[2];
        Float price = Float.parseFloat(productInfos[3]);
        Float weight = Float.parseFloat(productInfos[4]);
        String color = productInfos[5];
        Integer productCount = Integer.parseInt(productInfos[6]);
        Integer size = Integer.parseInt(productInfos[7]);
        Boolean isNaturalSkin = Boolean.parseBoolean(productInfos[8]);

        return new Boots(id, productName, price, weight, color, productCount, size, isNaturalSkin);
    }

    private static Product parseCloth(String strProduct)
    {
        String[] productInfos = strProduct.split("-");

        String productType = productInfos[0];
        Long id = Long.parseLong(productInfos[1]);
        String productName = productInfos[2];
        Float price = Float.parseFloat(productInfos[3]);
        Float weight = Float.parseFloat(productInfos[4]);
        String color = productInfos[5];
        Integer productCount = Integer.parseInt(productInfos[6]);
        String size = productInfos[7];
        String material = productInfos[8];

        return new Cloth(id, productName, price, weight, color, productCount, size, material);
    }

    private static Product parseProduct(String strProduct)
    {
        String[] productInfos = strProduct.split("-");

        String productType = productInfos[0];
        Long id = Long.parseLong(productInfos[1]);
        String productName = productInfos[2];
        Float price = Float.parseFloat(productInfos[3]);
        Float weight = Float.parseFloat(productInfos[4]);
        String color = productInfos[5];
        Integer productCount = Integer.parseInt(productInfos[6]);

        return new Product(id, productName, price, weight, color, productCount);
    }
}
