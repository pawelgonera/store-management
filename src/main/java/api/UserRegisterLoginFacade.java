package api;

import entity.User;

public interface UserRegisterLoginFacade
{
    String registerLogin(User user);
    boolean loginUser(String login, String password);
}
