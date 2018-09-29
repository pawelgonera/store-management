package entity.parser;

import entity.enums.SkinType;
import org.junit.jupiter.api.Test;
import java.util.EnumSet;

import static org.junit.jupiter.api.Assertions.*;

public class SkinTypeParserTest
{
    private final String[] skinTypeArrPositive = {"NATURAL", "ARTIFICIAL"};
    private final String[] skinTypeArrNegative = {"natural", "artificial", "extraColor"};

    @Test
    public void testParseColors()
    {
        EnumSet<SkinType> skinTypeEnumSet = EnumSet.noneOf(SkinType.class);

        for(int i = 0; i < skinTypeArrPositive.length; i++)
        {
            skinTypeEnumSet.add(SkinTypeParser.getSkinType(skinTypeArrPositive[i]));
        }

        for(SkinType skinType : skinTypeEnumSet)
        {
            assertEquals(skinType.name(), skinTypeArrPositive[skinType.ordinal()]);
        }
    }

    @Test
    public void testParseColorsNegative()
    {
        EnumSet<SkinType> skinTypeEnumSet = EnumSet.noneOf(SkinType.class);

        for(int i = 0; i < skinTypeArrNegative.length - 1; i++)
        {
            skinTypeEnumSet.add(SkinTypeParser.getSkinType(skinTypeArrNegative[i]));
        }

        for(SkinType skinType : skinTypeEnumSet)
        {
            assertNotEquals(SkinType.DEFAULT, skinTypeArrNegative[skinType.ordinal()]);
        }
    }
}
