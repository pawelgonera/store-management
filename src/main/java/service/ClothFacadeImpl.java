package service;

import api.ClothFacade;
import entity.Cloth;
import exception.ClothMaterialValidFormatException;
import java.util.Collections;
import java.util.List;

public class ClothFacadeImpl implements ClothFacade
{
    ClothServiceImpl clothService = ClothServiceImpl.getInstance();

    private static ClothFacadeImpl instance = null;

    public ClothFacadeImpl()
    {

    }

    public static ClothFacadeImpl getInstance()
    {
        if(instance == null)
            instance = new ClothFacadeImpl();

        return instance;
    }

    @Override
    public String createCloth(Cloth cloth)
    {
        try
        {
            clothService.saveCloth(cloth);
        }catch (ClothMaterialValidFormatException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }

        return cloth.getProduct().getProductName() + " zosta(ł/a) poprawnie stworzon(y/a)";
    }

    @Override
    public List<Cloth> getAllCloths()
    {
        try
        {
            return clothService.getAllCloths();
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }
}
