package api;

import entity.User;
import exception.UserLoginAlreadyExistException;

import java.io.IOException;
import java.util.List;

public interface UserDao
{
    void saveUser(User user) throws IOException;

    void saveUsers(List<User> users) throws IOException;

    void removeUserById(Long id) throws IOException;

    void removeUserByLogin(String login) throws IOException;

    List<User> getAllUsers() throws IOException;

}
