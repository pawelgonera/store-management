package service;

import api.UserRegisterLoginFacade;
import entity.User;
import exception.UserShortLengthLoginException;

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
    public String registerLogin(User user)
    {
        try
        {
            userService.addUser(user);

        }catch (Exception e)
        {
            return e.getMessage();
        }

        return "Dane są poprawne. Zarejestrowano nowego użytkownika";
    }

    @Override
    public boolean loginUser(String login, String password)
    {
        return userService.isCorrectLoginAndPassword(login, password);
    }
}
