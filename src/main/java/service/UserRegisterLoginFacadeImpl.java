package service;

import api.UserRegisterLoginFacade;
import entity.User;
import exception.UserLoginAlreadyExistException;
import exception.UserShortLengthLoginException;
import exception.UserShortLengthPasswordException;

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

        }catch (UserLoginAlreadyExistException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }catch (UserShortLengthLoginException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }catch (UserShortLengthPasswordException e)
        {
            e.printStackTrace();
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
