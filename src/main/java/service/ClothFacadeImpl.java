package service;

import api.ClothFacade;
import dao.ClothDaoImpl;
import entity.Cloth;

import java.util.List;

public class ClothFacadeImpl implements ClothFacade
{

    private ClothDaoImpl clothDao = ClothDaoImpl.getInstance();

    @Override
    public String createCloth(Cloth cloth)
    {
        clothDao.createCloth(cloth);

        return null;
    }

    @Override
    public String removeCloth(String clothName)
    {
        return null;
    }

    @Override
    public List<Cloth> getAllCloths()
    {
        return null;
    }
}
