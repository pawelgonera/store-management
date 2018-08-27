package entity.parser;

import entity.User;

public class UserParser
{
    public static User parseUser(String strUser)
    {
        String[] userInfos = strUser.split("-");

        Long id = Long.parseLong(userInfos[0]);
        String login = userInfos[1];
        String password = userInfos[2];

        return new User(id, login, password);
    }
}
