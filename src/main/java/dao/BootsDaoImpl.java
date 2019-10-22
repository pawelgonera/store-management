package dao;

import api.BootsDao;
import entity.Boots;;
import entity.parser.ProductParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class BootsDaoImpl implements BootsDao
{
    private Connection connection;
    private static final String databaseName = "store_project";
    private static final String tableName = "boots";
    private static final String user = "root";
    private static String pswd = "admin";

    private static BootsDaoImpl instance = null;

    public BootsDaoImpl()
    {
        init();
    }

    private void init()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/" + databaseName + "?useSSL=false", user, pswd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static BootsDaoImpl getInstance()
    {
        if (instance == null)
            instance = new BootsDaoImpl();

        return instance;
    }


    @Override
    public void createBoots(Boots boots)
    {
        PreparedStatement statement;
        try
        {
            Integer productId = boots.getProduct().getId();

            String query = "INSERT INTO " + tableName + " (size, skinType, product_id) VALUES(?, ?, ?)";
            statement = connection.prepareStatement(query);

            statement.setInt(1, boots.getSize());
            statement.setString(2, boots.isNaturalSkin().name());
            statement.setInt(3, productId);

            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteBootsById(Long bootsId)
    {
        PreparedStatement statement;
        try
        {
            String query = "DELETE FROM " + tableName + " WHERE id =  ?";
            statement = connection.prepareStatement(query);

            statement.setLong(1, bootsId);

            statement.execute();
            statement.close();

        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteBootsByProductId(Integer productId)
    {
        PreparedStatement statement;
        try
        {
            String query = "DELETE FROM " + tableName + " WHERE product_id =  ?";
            statement = connection.prepareStatement(query);

            statement.setInt(1, productId);

            statement.execute();
            statement.close();

        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public List<Boots> getAllBoots()
    {
        List<Boots> boots = new ArrayList<Boots>();
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
                for(int i = 1; i <= columns; i++)
                {
                    row.addElement(resultSet.getObject(i));
                }

                Boots boot = ProductParser.parseBoots(row);

                boots.add(boot);
            }


        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return boots;
    }
}
