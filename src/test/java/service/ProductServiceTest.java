package service;

import entity.Product;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ProductServiceTest
{
    @Test
    public void testGetAllProductsPositive()
    {
        List<Product> products = new ArrayList<Product>();
        products.add(new Product(1l, "Adiddas", 249.99f, 1.5f, "White", 2));

        ProductServiceImpl productService = new ProductServiceImpl(products);
        List<Product> productsFromTestClass =  productService.getAllProducts();

        Assert.assertEquals(products, productsFromTestClass);
    }

    @Test
    public void testGetAllProductsNegative()
    {
        List<Product> products = new ArrayList<Product>();
        products.add(new Product(1l, "Adiddas", 249.99f, 1.5f, "White", 2));

        ProductServiceImpl productService = new ProductServiceImpl(new ArrayList<Product>(products));
        products.add(new Product(3l, "Reserved", 89.99f, 0.3f, "Blue", 1));
        List<Product> productsFromTestClass =  productService.getAllProducts();

        Assert.assertNotEquals(products, productsFromTestClass);
    }

    @Test
    public void testGetProductCountOnListWithProducts()
    {
        List<Product> products = new ArrayList<Product>();
        products.add(new Product(1l, "Adiddas", 249.99f, 1.5f, "White", 2));
        products.add(new Product(2l, "Lasocki", 149.99f, 1.7f, "Brown", 2));

        ProductServiceImpl productService = new ProductServiceImpl(products);
        final int productCountFromTestClass = productService.getProductCountOnList();

        Assert.assertEquals(2, productCountFromTestClass);
    }

    @Test
    public void testGetProductCountOnListWithoutProducts()
    {
        ProductServiceImpl productService = new ProductServiceImpl();
        final int productCountFromTestClass = productService.getProductCountOnList();

        Assert.assertEquals(0, productCountFromTestClass);
    }

    @Test
    public void testGetProductByNameWhenExist()
    {
        List<Product> products = new ArrayList<Product>();
        Product product = new Product(1l, "Adiddas", 249.99f, 1.5f, "White", 2);
        products.add(product);

        ProductServiceImpl productService = new ProductServiceImpl(products);
        final Product productFromTestClass = productService.getProductByName("Adiddas");

        Assert.assertEquals(product, productFromTestClass);
    }

    @Test
    public void testGetProductByNameWhenNoExist()
    {
        List<Product> products = new ArrayList<Product>();
        Product product = new Product(1l, "Adiddas", 249.99f, 1.5f, "White", 2);
        products.add(product);

        ProductServiceImpl productService = new ProductServiceImpl(products);
        final Product productFromTestClass = productService.getProductByName("Something");

        Assert.assertEquals(null, productFromTestClass);
    }

    @Test
    public void testIsProductOnWarehouseWhenItIs()
    {
        List<Product> products = new ArrayList<Product>();
        products.add(new Product(1l, "Adiddas", 249.99f, 1.5f, "White", 2));

        ProductServiceImpl productService = new ProductServiceImpl(products);
        final boolean isMoreFromTestClass = productService.isProductOnWarehouse("Adiddas");

        Assert.assertTrue(isMoreFromTestClass);
    }

    @Test
    public void testIsProductOnWarehouseWhenItIsNot()
    {
        List<Product> products = new ArrayList<Product>();
        products.add(new Product(1l, "Adiddas", 249.99f, 1.5f, "White", 0));

        ProductServiceImpl productService = new ProductServiceImpl(products);
        final boolean isMoreFromTestClass = productService.isProductOnWarehouse("Adiddas");

        Assert.assertFalse(isMoreFromTestClass);
    }

    @Test
    public void testIsProductExistByNameWhenExist()
    {
        List<Product> products = new ArrayList<Product>();
        products.add(new Product(1l, "Adiddas", 249.99f, 1.5f, "White", 0));

        ProductServiceImpl productService = new ProductServiceImpl(products);
        final boolean isExistFromTestClass = productService.isProductExistByName("Adiddas");

        Assert.assertTrue(isExistFromTestClass);
    }

    @Test
    public void testIsProductExistByNameWhenNoExist()
    {
        List<Product> products = new ArrayList<Product>();
        products.add(new Product(1l, "Adiddas", 249.99f, 1.5f, "White", 0));

        ProductServiceImpl productService = new ProductServiceImpl(products);
        final boolean isExistFromTestClass = productService.isProductExistByName("Something");

        Assert.assertFalse(isExistFromTestClass);
    }

    @Test
    public void testIsProductExistByIdWhenExist()
    {
        List<Product> products = new ArrayList<Product>();
        products.add(new Product(1l, "Adiddas", 249.99f, 1.5f, "White", 0));

        ProductServiceImpl productService = new ProductServiceImpl(products);
        final boolean isExistFromTestClass = productService.isProductExistById(1l);

        Assert.assertTrue(isExistFromTestClass);
    }

    @Test
    public void testIsProductExistByIdWhenNoExist()
    {
        List<Product> products = new ArrayList<Product>();
        products.add(new Product(1l, "Adiddas", 249.99f, 1.5f, "White", 0));

        ProductServiceImpl productService = new ProductServiceImpl(products);
        final boolean isExistFromTestClass = productService.isProductExistById(2l);

        Assert.assertFalse(isExistFromTestClass);
    }
}
