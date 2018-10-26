package dao;

import api.ClothDao;
import api.ClothService;
import entity.Boots;
import entity.Cloth;
import entity.parser.ProductParser;
import service.ClothServiceImpl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ClothDaoImpl implements ClothDao
{
    private Connection connection;
    private static final String databaseName = "store_project";
    private static final String tableName = "cloths";
    private static final String user = "root";
    private static String pswd;
    private String fileName = ".idea/pswd_data/pswd.bin";

    private ClothServiceImpl clothService = ClothServiceImpl.getInstance();

    private static ClothDaoImpl instance = null;

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

    public ClothDaoImpl()
    {
        getPass();
        init();
    }

    public static ClothDaoImpl getInstance()
    {
        if (instance == null)
            instance = new ClothDaoImpl();

        return instance;
    }


    @Override
    public void createCloth(Cloth cloth)
    {
        PreparedStatement statement;
        try
        {
            Long productId = cloth.getProduct().getId();

            String query = "INSERT INTO " + tableName + " (size, material, product_id) VALUES(?, ?, ?)";
            statement = connection.prepareStatement(query);

            statement.setInt(1, cloth.getSize());
            statement.setString(2, cloth.getMaterial().name());
            statement.setLong(2, productId);

            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteClothById(Long clothId)
    {
        PreparedStatement statement;
        try
        {
            String query = "DELETE FROM " + tableName + "WHERE id =  ?";
            statement = connection.prepareStatement(query);

            statement.setLong(1, clothId);

            statement.execute();
            statement.close();

        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteClothByName(String clothName)
    {
        PreparedStatement statement;
        Cloth cloth = clothService.getClothByName(clothName);
        Long productId = cloth.getProduct().getId();
        try
        {
            String query = "DELETE FROM " + tableName + "WHERE product_id =  ?";
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
    public List<Cloth> getAllCloths()
    {
        List<Cloth> cloths = new ArrayList<Cloth>();
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

                Cloth cloth = ProductParser.parseCloth(row);

                cloths.add(cloth);
            }


        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return cloths;
    }
}
