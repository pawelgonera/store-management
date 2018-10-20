package entity.parser;

import entity.enums.Colors;
import org.junit.jupiter.api.Test;

import java.util.EnumSet;

import static org.junit.jupiter.api.Assertions.*;

public class ColorParserTest
{
    private final EnumSet<Colors> colors = EnumSet.allOf(Colors.class);
    private final String[] colorsArrPositive = {"BLACK", "WHITE", "RED", "GREEN", "BLUE", "YELLOW"};
    private final String[] colorsArrNegative = {"black", "white", "red", "green", "blue", "yellow", "extraColor"};


    @Test
    public void testParseColors()
    {
        EnumSet<Colors> colorsEnumSet = EnumSet.noneOf(Colors.class);

        for(int i = 0; i < colorsArrPositive.length; i++)
        {
            colorsEnumSet.add(ColorParser.getColor(colorsArrPositive[i]));
        }

        for(Colors color : colorsEnumSet)
        {
            assertEquals(color.name(), colorsArrPositive[color.ordinal()]);
        }
    }

    @Test
    public void testParseColorsNegative()
    {
        EnumSet<Colors> colorsEnumSet = EnumSet.noneOf(Colors.class);

        for(int i = 0; i < colorsArrNegative.length - 1; i++)
        {
            colorsEnumSet.add(ColorParser.getColor(colorsArrNegative[i]));
        }

        for(Colors color : colorsEnumSet)
        {
            assertNotEquals(Colors.DEFAULT, colorsArrNegative[color.ordinal()]);
        }
    }

}