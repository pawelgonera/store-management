package validator;

import entity.Cloth;
import exception.ClothMaterialValidFormatException;

public class ClothValidator
{
    private static ClothValidator instance = null;

    public ClothValidator()
    {

    }

    public static ClothValidator getInstance()
    {
        if(instance == null)
            instance = new ClothValidator();

        return instance;
    }

    public boolean isValidate(Cloth cloth) throws ClothMaterialValidFormatException
    {
        if(!isValidMaterialFormat(cloth.getMaterial().name()))
        {
            throw  new ClothMaterialValidFormatException("Wrong format of material " + cloth.getMaterial().name());
        }

        return true;
    }

    private boolean isValidMaterialFormat(String material)
    {
        if(material.equals(material.toUpperCase()))
            return true;
        else
            return false;
    }
}
