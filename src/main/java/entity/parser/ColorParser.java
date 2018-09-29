package entity.parser;

import entity.enums.Colors;

public class ColorParser
{
    public static Colors getColor(String str)
    {
        if(str.equals("BLACK"))
        {
            return Colors.BLACK;
        }
        else if(str.equals("WHITE"))
        {
            return Colors.WHITE;
        }
        else if (str.equals("RED"))
        {
            return Colors.RED;
        }
        else if (str.equals("GREEN"))
        {
            return Colors.GREEN;
        }
        else if (str.equals("BLUE"))
        {
            return Colors.BLUE;
        }
        else if (str.equals("YELLOW"))
        {
            return Colors.YELLOW;
        }

        return Colors.DEFAULT;
    }
}
