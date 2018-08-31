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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class UserServiceImpl implements UserService
{
    //List <User> users;

    UserDao userDao = UserDaoImpl.getInstance();
    UserValidator userValidator = UserValidator.getInstance();

    public UserServiceImpl()

    {
       // this.users = new ArrayList<User>();
    }

    public UserServiceImpl(List <User> users)
    {
        //this.users = users;
    }

    @Override
    public List<User> getAllUsers() throws IOException {

        return userDao.getAllUsers();
    }

    @Override
    public void addUser(User user) throws UserShortLengthPasswordException, UserLoginAlreadyExistException, UserShortLengthLoginException, IOException
    {
        if(userValidator.isValidate(user))
            userDao.saveUser(user);
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
