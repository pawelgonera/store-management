package entity.parser;

import entity.enums.SkinType;

public class SkinTypeParser
{
    public static SkinType getSkinType(String str)
    {
        if(str.equals("NATURAL"))
        {
            return SkinType.NATURAL;
        }
        else if (str.equals("ARTIFICIAL"))
        {
            return SkinType.ARTIFICIAL;
        }

        return SkinType.DEFAULT;
    }
}
