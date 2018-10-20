package api;

import entity.User;
import exception.UserLoginAlreadyExistException;

import java.io.IOException;
import java.util.List;

public interface UserDao
{
    void createUser(User user);

    void removeUserById(Long id);

    void removeUserByLogin(String login);

    List<User> getAllUsers();

}
