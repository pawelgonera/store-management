package service;

import api.UserService;
import entity.User;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class UserServiceImpl implements UserService
{

    List <User> users;

    public UserServiceImpl()

    {
        this.users = new ArrayList<User>();
    }

    public UserServiceImpl(List <User> users)
    {
        this.users = users;
    }

    @Override
    public List<User> getAllUsers()
    {
        return  this.users;
    }

    @Override
    public void addUser(User user)
    {
        this.users.add(user);
    }

    @Override
    public void removeUserById(Long id)
    {
        for(int i = 0; i<users.size(); i++)
        {
            User user = users.get(i);
            if (user.getId() == id)
                users.remove(i);
        }

    }
}
