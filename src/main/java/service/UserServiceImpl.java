package service;

import api.UserDao;
import api.UserService;
import dao.UserDaoImpl;
import entity.User;
import exception.UserLoginAlreadyExistException;
import validator.UserValidator;
import java.io.IOException;
import java.util.List;


public class UserServiceImpl implements UserService
{
    private static UserServiceImpl instance = null;

    private UserDao userDao = UserDaoImpl.getInstance();
    private UserValidator userValidator = UserValidator.getInstance();

    public UserServiceImpl()
    {

    }

    public static UserServiceImpl getInstance()
    {
        if(instance == null)
            instance = new UserServiceImpl();

        return instance;
    }

    @Override
    public List<User> getAllUsers()
    {

        return userDao.getAllUsers();
    }

    @Override
    public boolean addUser(User user)
    {
        try
        {
            if(isLoginAlreadyExist(user.getLogin()))
            {
                throw  new UserLoginAlreadyExistException();
            }

            if(userValidator.isValidate(user))
            {
                userDao.createUser(user);
                return true;
            }

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }


        return false;
    }

    private boolean isLoginAlreadyExist(String login)
    {
        User user = getUserByLogin(login);

        return user != null;
    }

    @Override
    public void removeUserById(Long id)
    {
        userDao.deleteUserById(id);
    }

    @Override
    public User getUserById(Long id)
    {
        List<User> users = getAllUsers();

        for(User user : users)
        {
            if(user.getId() == id)
                return user;
        }

        return null;
    }

    @Override
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

    @Override
    public boolean isCorrectLoginAndPassowrd(String login, String password)
    {
        User user = getUserByLogin(login);

        if(user.getLogin().equals(login) && user.getPassword().equals(password))
            return true;
        else
            return false;
    }
}
