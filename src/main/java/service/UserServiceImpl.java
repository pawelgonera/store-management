package service;

import api.UserDao;
import api.UserService;
import dao.UserDaoImpl;
import entity.User;
import exception.UserLoginAlreadyExistException;
import exception.UserShortLengthLoginException;
import exception.UserShortLengthPasswordException;
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
    public List<User> getAllUsers() throws IOException {

        return userDao.getAllUsers();
    }

    @Override
    public boolean addUser(User user) throws UserShortLengthPasswordException, UserLoginAlreadyExistException, UserShortLengthLoginException, IOException
    {
        if(userValidator.isValidate(user))
        {
            userDao.saveUser(user);
            return  true;
        }

        return false;
    }

    public boolean isCorrectLoginAndPassowrd(String login, String password) throws IOException
    {
        User user = userDao.getUserByLogin(login);

        if(user.getLogin().equals(login) && user.getPassword().equals(password))
            return true;
        else
            return false;
    }

    @Override
    public void removeUserById(Long id) throws IOException
    {
        List<User> users = getAllUsers();
        for(int i = 0; i<users.size(); i++)
        {
            User user = users.get(i);
            if (user.getId() == id)
                users.remove(i);
        }

        userDao.saveUsers(users);

    }
}
