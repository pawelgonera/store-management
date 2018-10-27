package dao;

import api.BootsDao;
import entity.Boots;
import entity.Cloth;
import entity.parser.ProductParser;
import service.BootsServiceImpl;

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
    private static String pswd;
    private String fileName = ".idea/pswd_data/pswd.bin";

    private static BootsDaoImpl instance = null;

    private void getPass()
    {
        try
        {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));

            pswd = bufferedReader.readLine();

            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public BootsDaoImpl()
    {
        getPass();
        init();
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

            String query = "INSERT INTO " + tableName + " (size, material, product_id) VALUES(?, ?, ?)";
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
            String query = "DELETE FROM " + tableName + "WHERE id =  ?";
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
    public void deleteBootsByName(String bootsName)
    {
        PreparedStatement statement;
        Boots boot = getBootsByName(bootsName);
        Integer productId = boot.getProduct().getId();
        try
        {
            String query = "DELETE FROM " + tableName + "WHERE product_id =  ?";
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
    public Boots getBootsByName(String bootsName)
    {
        List<Boots> boots = getAllBoots();

        for(Boots boot: boots)
        {
            if(boot.getProduct().getProductName().equals(bootsName))
            {
                return boot;
            }
        }

        return null;
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
                for(int i = 0; i <= columns; i++)
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
