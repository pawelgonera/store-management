package service;

import api.ClothService;
import dao.ClothDaoImpl;
import entity.Cloth;
import exception.ClothMaterialValidFormatException;
import validator.ClothValidator;

import java.util.List;

public class ClothServiceImpl implements ClothService
{
    private static ClothServiceImpl instance = null;
    private ClothDaoImpl clothDao = ClothDaoImpl.getInstance();
    private ClothValidator clothValidator = ClothValidator.getInstance();

    public ClothServiceImpl()
    {

    }

    public static ClothServiceImpl getInstance()
    {
        if(instance == null)
            instance = new ClothServiceImpl();

        return instance;
    }

    @Override
    public List<Cloth> getAllCloths()
    {
        return clothDao.getAllCloths();
    }

    @Override
    public Integer getClothCountOnList()
    {
        return clothDao.getAllCloths().size();
    }

    @Override
    public boolean isClothOnWarehouse(String clothName)
    {
        List<Cloth> cloths;
        cloths = clothDao.getAllCloths();

        for(int i = 0; i< cloths.size(); i++)
        {
            Cloth cloth = cloths.get(i);
            if(cloth.getProduct().getProductName().equals(clothName) && cloth.getProduct().getProductCount() > 0)
            {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean isClothExist(String clothName)
    {
        Cloth cloth;
        cloth = getClothByName(clothName);

        if(cloth == null)
            return false;

        return true;
    }

    @Override
    public boolean isClothExist(Integer id)
    {
        Cloth cloth;
        cloth = getClothById(id);

        if(cloth == null)
            return false;

        return true;
    }

    @Override
    public boolean saveCloth(Cloth cloth) throws ClothMaterialValidFormatException
    {
        if(clothValidator.isValidate(cloth))
        {
            clothDao.createCloth(cloth);
            return true;
        }

        return  false;
    }

    @Override
    public Cloth getClothById(Integer clothtId)
    {
        List<Cloth> cloths = getAllCloths();

        for(Cloth cloth : cloths)
        {
            if(cloth.getProduct().getId() == clothtId)
                return cloth;
        }

        return null;
    }

    @Override
    public Cloth getClothByName(String clothName)
    {
        List<Cloth> cloths = getAllCloths();

        for(Cloth cloth : cloths)
        {
            if(cloth.getProduct().getProductName().equals(clothName))
                return cloth;
        }

        return null;
    }

    @Override
    public void removeCloth(Integer productId)
    {
        clothDao.deleteClothByProductId(productId);
    }
}
