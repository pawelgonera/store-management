package service;

import dao.UserRegisterLoginFacade;
import entity.User;
import exception.UserLoginAlreadyExistException;
import exception.UserShortLengthLoginException;
import exception.UserShortLengthPasswordException;

import java.io.IOException;

public class UserRegisterLoginFacadeImpl implements UserRegisterLoginFacade
{
    private static UserRegisterLoginFacadeImpl instance = null;
    private UserServiceImpl userService;

    public UserRegisterLoginFacadeImpl() throws IOException
    {
        userService = new UserServiceImpl();
    }

    public static UserRegisterLoginFacadeImpl getInstance() throws IOException
    {
        if(instance == null)
            instance = new UserRegisterLoginFacadeImpl();

        return instance;
    }

    @Override
    public boolean registerLogin(User user) throws UserLoginAlreadyExistException, UserShortLengthPasswordException, UserShortLengthLoginException, IOException
    {
        return userService.addUser(user);
    }

    @Override
    public boolean loginUser(String login, String password) throws IOException
    {
        return userService.isCorrectLoginAndPassowrd(login, password);
    }
}
