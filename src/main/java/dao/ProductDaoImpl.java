package dao;

import api.ProductDao;
import entity.Product;
import entity.enums.Colors;
import entity.parser.ProductParser;
import validator.ProductValidator;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao
{
    private Connection connection;
    private static final String databaseName = "store_project";
    private static final String tableName = "products";
    private static final String user = "rooot";
    private static String pswd;
    private String fileName = ".idea/pswd_data/pswd.bin";

    private static ProductDaoImpl instance = null;

    private ProductValidator productValidator = ProductValidator.getInstance();

    private void getPass()
    {
        try
        {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));

            pswd = bufferedReader.readLine();

            bufferedReader.close();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void init()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/" + databaseName + "?useSSL=false", user, pswd);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public ProductDaoImpl()
    {
        getPass();
        init();
    }

    public static ProductDaoImpl getInstance()
    {
        if(instance == null)
            instance = new ProductDaoImpl();

        return instance;
    }

    @Override
    public void createProduct(Product product)
    {
       PreparedStatement statement;
       try
       {
           char productType = product.toString().charAt(0);

           String query = "INSERT INTO " + tableName + " (productType, productName, price, weight, color, productCount) VALUES(?, ?, ?, ?, ?, ?)";
           statement = connection.prepareStatement(query);

           statement.setString(1, productType + "");
           statement.setString(2, product.getProductName());
           statement.setFloat(3, product.getPrice());
           statement.setFloat(4, product.getWeight());
           statement.setString(5, product.getColor().name());
           statement.setInt(6, product.getProductCount());

           statement.execute();
           statement.close();
       }catch (SQLException e)
       {
           e.printStackTrace();
       }
    }

    @Override
    public void deleteProductById(Long productId)
    {
        PreparedStatement statement;
        try
        {
            String query = "DELETE FROM " + tableName + " WHERE id = ?";
            statement = connection.prepareStatement(query);

            statement.setLong(1, productId);

            statement.execute();
            statement.close();

        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteProductByName(String productName)
    {
        PreparedStatement statement;
        try
        {
            String query = "DELETE FROM " + tableName + " WHERE id = ?";
            statement = connection.prepareStatement(query);

            statement.setString(1, productName);

            statement.execute();
            statement.close();

        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> getAllProducts()
    {
        List<Product> products = new ArrayList<Product>();

        Statement statement;
        try
        {
            String query = "SELECT * FROM " + tableName;
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next())
            {
                String productType = resultSet.getString("productType");
                Integer id = resultSet.getInt("id");
                String productName = resultSet.getString("productName");;
                Float price = resultSet.getFloat("price");
                Float weight = resultSet.getFloat("weight");
                String color = resultSet.getString("color");
                Integer productCount = resultSet.getInt("productCount");
                Integer size = resultSet.getInt("size");
                String material = resultSet.getString("material");
                String skinType = resultSet.getString("skinType");

                Product product = new Product(id, productName, price, weight, Colors.valueOf(color), productCount);

                products.add(product);
            }

            statement.close();

        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return products;
    }

}
