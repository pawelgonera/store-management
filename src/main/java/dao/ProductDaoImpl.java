package dao;

import api.ProductDao;
import entity.Product;
import entity.parser.ProductParser;
import exception.ProductCountNegativeException;
import exception.ProductNameEmptyException;
import exception.ProductPriceNoPositiveException;
import exception.ProductWeightNoPositiveException;
import utils.FileUtils;
import validator.ProductValidator;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao
{
    private String fileName = "products.txt";
    private static ProductDaoImpl instance = null;
    private ProductValidator productValidator = ProductValidator.getInstance();

    public ProductDaoImpl() throws IOException {
        try
        {
            FileUtils.createNewFile(fileName);
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static ProductDaoImpl getInstance() throws IOException
    {
        if(instance == null)
            instance = new ProductDaoImpl();

        return instance;
    }

    @Override
    public void saveProduct(Product product) throws IOException
    {
       List<Product> products = getAllProducts();

       try
       {
           if (productValidator.isValidate(product)) {
               products.add(product);

               saveProducts(products);
           }
       }
       catch (Exception e)
       {
           System.out.println(e.getMessage());
       }
    }

    @Override
    public void saveProducts(List<Product> products) throws IOException
    {
        FileUtils.clearFiler(fileName);
        BufferedWriter writer = new BufferedWriter(new PrintWriter(new FileOutputStream(fileName, false)));

        for (Product product : products) {
            writer.write(product.toString());
            writer.newLine();
        }

        writer.close();

    }

    @Override
    public void removeProductById(Long productId) throws IOException
    {
        List<Product> products = getAllProducts();

        for (int i = 0; i < products.size(); i++)
        {
            if (products.get(i).getId() == productId)
                products.remove(i);
        }

        saveProducts(products);
    }

    @Override
    public void removeProductByName(String productName) throws IOException
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

        BufferedReader reader = new BufferedReader(new FileReader(fileName));

        String readLine;

        while((readLine = reader.readLine()) != null)
        {
            Product product = ProductParser.convertProduct(readLine);
            if(product != null)
                products.add(product);
        }

        reader.close();

        return products;
    }

}
