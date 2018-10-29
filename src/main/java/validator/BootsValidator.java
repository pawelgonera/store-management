package validator;

import entity.Boots;
import exception.BootsSkinTypeValidFormatException;
import exception.ClothMaterialValidFormatException;

public class BootsValidator
{
    private static BootsValidator instance = null;

    public BootsValidator()
    {

    }

    public static BootsValidator getInstance()
    {
        if(instance == null)
            instance = new BootsValidator();

        return instance;
    }

    public boolean isValidate(Boots boots) throws BootsSkinTypeValidFormatException
    {
        if(!isValidSkinTypeFormat(boots.isNaturalSkin().name()))
        {
            throw  new BootsSkinTypeValidFormatException("Wrong format of material " + boots.isNaturalSkin().name());
        }

        return true;
    }

    private boolean isValidSkinTypeFormat(String skintype)
    {
        if(skintype.equals(skintype.toUpperCase()))
            return true;
        else
            return false;
    }
}
