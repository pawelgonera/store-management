package dao;

import api.ProductDao;
import entity.Product;
import entity.parser.ProductParser;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ProductDaoImpl implements ProductDao
{
    private Connection connection;
    private static final String databaseName = "store_project";
    private static final String tableName = "products";
    private static final String user = "root";
    private static String pswd = "admin";

    private static ProductDaoImpl instance = null;

    public ProductDaoImpl()
    {
        init();
    }

    private void init() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/" + databaseName + "?useSSL=false", user, pswd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ProductDaoImpl getInstance() {
        if (instance == null)
            instance = new ProductDaoImpl();

        return instance;
    }

    @Override
    public void createProduct(Product product)
    {
        PreparedStatement statement;
        try
        {
            String query = "INSERT INTO " + tableName + " (productName, price, weight, color, productCount) VALUES(?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(query);

            statement.setString(1, product.getProductName());
            statement.setFloat(2, product.getPrice());
            statement.setFloat(3, product.getWeight());
            statement.setString(4, product.getColor().name());
            statement.setInt(5, product.getProductCount());

            statement.execute();
            statement.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void updateProduct(Product product)
    {
        PreparedStatement statement;
        try
        {
            String query = "UPDATE " + tableName + " SET productName = ?, price = ?, weight = ?, color = ?, productCount = ? WHERE id = ?";
            statement = connection.prepareStatement(query);

            statement.setString(1, product.getProductName());
            statement.setFloat(2, product.getPrice());
            statement.setFloat(3, product.getWeight());
            statement.setString(4, product.getColor().name());
            statement.setInt(5, product.getProductCount());
            statement.setInt(6, product.getId());

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
        try {
            String query = "DELETE FROM " + tableName + " WHERE id = ?";
            statement = connection.prepareStatement(query);

            statement.setLong(1, productId);

            statement.execute();
            statement.close();

        } catch (SQLException e) {
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

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public Product getProductById(Long productId)
    {
        Statement statement;
        Product product = null;
        try
        {
            String query = "SELECT * FROM " + tableName + " WHERE id = " + productId;
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columns = metaData.getColumnCount();

            Vector row = new Vector(columns);
            for (int i = 1; i <= columns; i++)
            {
                row.addElement(resultSet.getObject(i));
            }

            product = ProductParser.parseProduct(row);

            statement.close();

        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return product;
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

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columns = metaData.getColumnCount();

            while (resultSet.next())
            {
                Vector row = new Vector(columns);
                for (int i = 1; i <= columns; i++)
                {
                    row.addElement(resultSet.getObject(i));
                }

                Product product = ProductParser.parseProduct(row);

                products.add(product);
            }

            statement.close();

        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return products;

    }
}
