package entity.parser;

import entity.enums.Material;

public class MaterialParser
{
    public static Material getMaterial(String str)
    {
        if(str.equals("LEATHER"))
        {
            return Material.LEATHER;
        }
        else if (str.equals("FUR"))
        {
            return Material.FUR;
        }
        else if (str.equals("COTTON"))
        {
            return Material.COTTON;
        }
        else if (str.equals("WOOL"))
        {
            return Material.WOOL;
        }
        else if (str.equals("POLYESTERS"))
        {
            return Material.POLYESTERS;
        }

        return Material.DEFAULT;
    }
}
