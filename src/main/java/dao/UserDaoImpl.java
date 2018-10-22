package dao;

import api.UserDao;
import entity.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao
{
    Connection connection;
    private static final String databaseName = "store_project";
    private static final String tableName = "users";
    private static final String user = "root";
    private static final String fileName = ".idea/pswd_data/pswd.bin";
    private static String pswd;

    private static UserDaoImpl instance = null;

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
            System.out.println("Error with connect to MySQL database");
        }
    }


    public UserDaoImpl()
    {
        getPass();
        init();
    }

    public static UserDaoImpl getInstance()
    {
        if(instance == null)
            instance = new UserDaoImpl();

        return instance;
    }

    @Override
    public void createUser(User user)
    {
        PreparedStatement preparedStatement;
        try
        {
            String query = "INSERT INTO " + tableName + " (login, password) VALUES(?, ?)";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());

            preparedStatement.execute();
            preparedStatement.close();
        }catch (SQLException e)
        {
            System.err.println("Can't create new user in MySQL database");
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUserById(Long userId)
    {
        PreparedStatement preparedStatement;
        try
        {
            String query = "DELETE FROM " + tableName + " WHERE id = ?";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setLong(1, userId);

            preparedStatement.execute();
            preparedStatement.close();
        }catch (SQLException e)
        {
            System.err.println("Can't delete user from MySQL database");
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUserByLogin(String login)
    {
        PreparedStatement preparedStatement;

        try
        {
            String query = "DELETE FROM " + tableName + " WHERE login = ?";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, login);

            preparedStatement.execute();
            preparedStatement.close();
        }catch (SQLException e)
        {
            System.err.println("Can't delete user from MySQL database");
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers()
    {
        List<User> users = new ArrayList<User>();
        Statement statement;

        try
        {
            String query = "SELECT * FROM " + tableName;
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next())
            {
                Integer id = resultSet.getInt("id");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");

                User user = new User(id, login, password);
                users.add(user);
            }

            statement.close();

        }catch (SQLException e)
        {
            System.err.println("Can't get the users from MySQL database");
            e.printStackTrace();
        }

        return users;
    }

    public User getUserByLogin(String userLogin)
    {
       Statement statement;
       try
       {
           String query = "SELECT * FROM " + tableName + " WHERE login = '" + userLogin + "'";
           statement = connection.createStatement();
           ResultSet resultSet = statement.executeQuery(query);

           while(resultSet.next())
           {
               Integer id = resultSet.getInt("id");
               String login = resultSet.getString("login");
               String password = resultSet.getString("password");

               User user = new User(id, login, password);

               return user;
           }

           statement.close();

       }catch (SQLException e)
       {
           System.out.println("Can't get the user from MySQL database");
           e.printStackTrace();
       }

       return null;
    }
}
