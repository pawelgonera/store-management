package entity.parser;

import entity.enums.Material;
import org.junit.jupiter.api.Test;
import java.util.EnumSet;

import static org.junit.jupiter.api.Assertions.*;

public class MaterialParserTest
{
    private final String[] materialArrPositive = {"LEATHER", "FUR", "COTTON", "WOOL", "POLYESTERS"};
    private final String[] materialArrNegative = {"leather", "fur", "cotton", "wool", "polyesters", "extraColor"};

    @Test
    public void testParseColors()
    {
        EnumSet<Material> materialEnumSet = EnumSet.noneOf(Material.class);

        for(int i = 0; i < materialArrPositive.length; i++)
        {
            materialEnumSet.add(MaterialParser.getMaterial(materialArrPositive[i]));
        }

        for(Material material : materialEnumSet)
        {
            assertEquals(material.name(), materialArrPositive[material.ordinal()]);
        }
    }

    @Test
    public void testParseColorsNegative()
    {
        EnumSet<Material> materialEnumSet = EnumSet.noneOf(Material.class);

        for(int i = 0; i < materialArrNegative.length - 1; i++)
        {
            materialEnumSet.add(MaterialParser.getMaterial(materialArrNegative[i]));
        }

        for(Material material : materialEnumSet)
        {
            assertNotEquals(Material.DEFAULT, materialArrNegative[material.ordinal()]);
        }
    }
}
