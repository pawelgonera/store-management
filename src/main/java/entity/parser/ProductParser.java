package entity.parser;

import dao.ProductDaoImpl;
import entity.Boots;
import entity.Cloth;
import entity.Product;
import entity.enums.Colors;
import entity.enums.Material;
import entity.enums.SkinType;
import service.ProductServiceImpl;

import java.util.Vector;

import static entity.parser.ColorParser.getColor;
import static entity.parser.MaterialParser.getMaterial;
import static entity.parser.SkinTypeParser.getSkinType;

public class ProductParser
{
    private static ProductDaoImpl productDao = ProductDaoImpl.getInstance();
    private static ProductServiceImpl productService = ProductServiceImpl.getInstance();

    public static Product parseProduct(Vector strProduct)
    {
        Object[] productInfos = strProduct.toArray();

        Integer id = Integer.parseInt(productInfos[0].toString());
        String productName = (String) productInfos[1];
        Float price = Float.parseFloat(productInfos[2].toString());
        Float weight = Float.parseFloat(productInfos[3].toString());
        String color = (String) productInfos[4];
        Colors colors = getColor(color);
        Integer productCount = Integer.parseInt(productInfos[5].toString());

        return new Product(id, productName, price, weight, colors, productCount);
    }

    public static Cloth parseCloth(Vector strProduct)
    {
        Object[] productInfos = strProduct.toArray();

        Long id = Long.parseLong(productInfos[0].toString());
        Integer size = Integer.parseInt(productInfos[1].toString());
        String material = (String) productInfos[2];
        Material materials = getMaterial(material);
        Long productId = Long.parseLong(productInfos[3].toString());

        Product product = productDao.getProductById(productId);

        return new Cloth(id, product, size, materials);
    }

    public static Boots parseBoots(Vector strProduct)
    {
        Object[] productInfos = strProduct.toArray();

        Long id = Long.parseLong(productInfos[0].toString());
        Integer size = Integer.parseInt(productInfos[1].toString());
        String skinType = (String) productInfos[2];
        SkinType skinTypes = getSkinType(skinType);
        Long productId = Long.parseLong(productInfos[3].toString());

        Product product = productService.getProductById(productId);

        return new Boots(id, product, size, skinTypes);
    }

}
