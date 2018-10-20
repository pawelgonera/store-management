package dao;

import api.UserDao;
import entity.User;
import entity.parser.UserParser;
import utils.FileUtils;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao
{
    Connection connection;
    private static final String databaseName = "users";
    private static final String user = "root";
    private static final String pswd = "pswd.bin";

    private static UserDaoImpl instance = null;

    public UserDaoImpl(String pswd)
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

    public static UserDaoImpl getInstance()
    {
        if(instance == null)
            instance = new UserDaoImpl(pswd);

        return instance;
    }

    @Override
    public void createUser(User user)
    {



    }

    @Override
    public void removeUserById(Long userId)
    {
        List<User> users = getAllUsers();

        for(int i = 0; i<users.size(); i++)
        {
            if(users.get(i).getId() == userId)
                users.remove(i);
        }

        saveUsers(users);
    }

    @Override
    public void removeUserByLogin(String login)
    {
        List<User> users = getAllUsers();

        for(int i = 0; i<users.size(); i++)
        {
            if(users.get(i).getLogin().equals(login))
                users.remove(i);
        }

        saveUsers(users);
    }

    @Override
    public List<User> getAllUsers()
    {
        List<User> users = new ArrayList<User>();
        Statement statement;

        try
        {
            String query = "SELECT * FROM " + databaseName;
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
            e.printStackTrace();
        }

        return users;
    }

    public User getUserByLogin(String login)
    {
        List<User> users = getAllUsers();

        for(User user : users)
        {
            if(user.getLogin().equals(login))
                return user;
        }

        return null;
    }
}
