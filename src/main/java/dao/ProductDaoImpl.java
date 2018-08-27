package dao;

import api.ProductDao;
import entity.Product;
import entity.parser.ProductParser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao
{
    File file;
    String productType;

    public ProductDaoImpl(String fileName, String productType)

    {
        this.file = new File(fileName + ".txt");
        this.productType = productType;
    }

    @Override
    public void saveProduct(Product product) throws IOException
    {
       List<Product> products = getAllProducts();

       products.add(product);

       saveProducts(products);
    }

    @Override
    public void saveProducts(List<Product> products) throws IOException
    {
        BufferedWriter writer = new BufferedWriter(new PrintWriter(new FileOutputStream(file, false)));

        for(Product product : products)
        {
            writer.write(product.toString());
            writer.newLine();
        }

        writer.close();
    }

    @Override
    public void removeProductById(Long productId) throws IOException
    {
        List<Product> products = getAllProducts();

        for(int i = 0; i<products.size(); i++)
        {
            if(products.get(i).getId() == productId)
                products.remove(i);
        }

        saveProducts(products);

    }

    @Override
    public void removePRoductByName(String productName) throws IOException
    {
        List<Product> products = getAllProducts();

        for(int i = 0; i<products.size(); i++)
        {
            if(products.get(i).getProductName().equals(productName))
                products.remove(i);
        }

        saveProducts(products);
    }

    @Override
    public List<Product> getAllProducts() throws IOException
    {
        List<Product> products = new ArrayList<Product>();

        BufferedReader reader = new BufferedReader(new FileReader(file));

        String readLine;

        while((readLine = reader.readLine()) != null)
        {
            Product product = ProductParser.convertProduct(readLine, productType);
            if(product != null)
                products.add(product);
        }

        reader.close();

        return products;
    }

    @Override
    public Product getProductById(Long productId) throws IOException
    {
        List<Product> products = getAllProducts();

        for(int i = 0; i<products.size(); i++)
        {
            if(products.get(i).getId() == productId)
                return products.get(i);
        }

        return null;
    }

    @Override
    public Product getProductByName(String productName) throws IOException
    {
        List<Product> products = getAllProducts();

        for(int i = 0; i<products.size(); i++)
        {
            if(products.get(i).getProductName().equals(productName))
                return products.get(i);
        }

        return null;
    }


}
