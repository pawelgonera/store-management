package service;

import api.UserRegisterLoginFacade;
import entity.User;


public class UserRegisterLoginFacadeImpl implements UserRegisterLoginFacade
{
    private static UserRegisterLoginFacadeImpl instance = null;
    private UserServiceImpl userService;

    public UserRegisterLoginFacadeImpl()
    {
        userService = new UserServiceImpl();
    }

    public static UserRegisterLoginFacadeImpl getInstance()
    {
        if(instance == null)
            instance = new UserRegisterLoginFacadeImpl();

        return instance;
    }

    @Override
    public boolean registerLogin(User user)
    {
        return userService.addUser(user);
    }

    @Override
    public boolean loginUser(String login, String password)
    {
        return userService.isCorrectLoginAndPassowrd(login, password);
    }
}
