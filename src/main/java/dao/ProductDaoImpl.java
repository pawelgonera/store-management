package dao;

import api.ProductDao;
import entity.Boots;
import entity.Cloth;
import entity.Product;
import entity.parser.ProductParser;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao
{
    private Connection connection;
    private static final String databaseName = "store_project";
    private static final String tableName = "products";
    private static final String user = "root";
    private static String pswd;
    private String fileName = ".idea/pswd_data/pswd.bin";

    private static ProductDaoImpl instance = null;

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

           String query = "INSERT INTO " + tableName + " (productType, productName, price, weight, color, productCount, size, material, skinType) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
           statement = connection.prepareStatement(query);

           statement.setString(1, productType + "");
           statement.setString(2, product.getProductName());
           statement.setFloat(3, product.getPrice());
           statement.setFloat(4, product.getWeight());
           statement.setString(5, product.getColor().name());
           statement.setInt(6, product.getProductCount());

           if(productType == 'C')
           {
               statement.setInt(7, ((Cloth) product).getSize());
               statement.setString(8, ((Cloth) product).getMaterial().name().toUpperCase());
           }
           else
           {
               statement.setInt(7, 0);
               statement.setString(8, " ");
           }

           if(productType == 'B')
           {
               statement.setInt(7, ((Boots) product).getSize());
               statement.setString(9, ((Boots) product).isNaturalSkin().name().toUpperCase());
           }
           else
           {
               statement.setInt(7, 0);
               statement.setString(9, " ");
           }

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
            String query = "DELETE FROM " + tableName + " WHERE productName = ?";
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
        StringBuilder sb = new StringBuilder();
        Statement statement;
        try
        {
            String query = "SELECT * FROM " + tableName;
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next())
            {
                sb.delete(0, sb.capacity());
                sb.append(resultSet.getString("productType"));
                sb.append("#");
                sb.append(resultSet.getInt("id"));
                sb.append("#");
                sb.append(resultSet.getString("productName"));
                sb.append("#");
                sb.append(resultSet.getFloat("price"));
                sb.append("#");
                sb.append(resultSet.getFloat("weight"));
                sb.append("#");
                sb.append(resultSet.getString("color"));
                sb.append("#");
                sb.append(resultSet.getInt("productCount"));
                sb.append("#");
                sb.append(resultSet.getInt("size"));
                sb.append("#");
                sb.append(resultSet.getString("material"));
                sb.append("#");
                sb.append(resultSet.getString("skinType"));

                /*
                String productType = resultSet.getString("productType")
                Integer id = resultSet.getInt("id")
                String productName = resultSet.getString("productName");;
                Float price = resultSet.getFloat("price");
                Float weight = resultSet.getFloat("weight");
                String color = resultSet.getString("color");
                Integer productCount = resultSet.getInt("productCount");
                Integer clothSize = resultSet.getInt("clothSize");
                Integer bootsSize = resultSet.getInt("bootsSize");
                String material = resultSet.getString("material");
                String skinType = resultSet.getString("skinType");
                */

                //Product product = new Product(id, productName, price, weight, Colors.valueOf(color), productCount);

                Product product = ProductParser.convertProduct(sb.toString());

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
