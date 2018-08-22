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
        //Iterator<User> iter = users.iterator();
       /* while(iter.hasNext())
        {
            User user = iter.next();
            if (user.getId() == id)
                iter.remove();
        }*/

        /*for(int i=0;i<users.size();i++)
        {

            User userFromList = users.get(i);

            if (userFromList.getId() == id)
            {

                users.remove(i);

                break;
            }
        }*/

        for(User user : users)
        {
            if(user.getId() == id)
            {
                users.remove(user);
                break;
            }

        }
    }
}
