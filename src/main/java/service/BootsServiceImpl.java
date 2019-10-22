package service;

import dao.BootsDaoImpl;
import api.BootsService;
import entity.Boots;
import exception.BootsSkinTypeValidFormatException;
import validator.BootsValidator;

import java.util.List;

public class BootsServiceImpl implements BootsService
{
    private static BootsServiceImpl instance = null;
    private BootsDaoImpl bootsDao = BootsDaoImpl.getInstance();
    private BootsValidator bootsValidator = BootsValidator.getInstance();

    public BootsServiceImpl()
    {

    }

    public static BootsServiceImpl getInstance()
    {
        if(instance == null)
            instance = new BootsServiceImpl();

        return instance;
    }

    @Override
    public List<Boots> getAllBoots()
    {
        return bootsDao.getAllBoots();
    }

    @Override
    public Integer getBootsCountOnList()
    {
        return bootsDao.getAllBoots().size();
    }

    @Override
    public boolean isBootsOnWarehouse(String bootsName)
    {
        List<Boots> boots;
        boots = bootsDao.getAllBoots();

        for(int i = 0; i < boots.size(); i++)
        {
            Boots boot = boots.get(i);
            if(boot.getProduct().getProductName().equals(bootsName) && boot.getProduct().getProductCount() > 0)
            {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean isBootsExist(String bootsName)
    {
        Boots boot;
        boot = getBootsByName(bootsName);

        if(boot == null)
            return false;

        return true;
    }

    @Override
    public boolean isBootsExist(Integer id)
    {
        Boots boot;
        boot = getBootsById(id);

        if(boot == null)
            return false;

        return true;
    }

    @Override
    public boolean saveBoots(Boots boot) throws BootsSkinTypeValidFormatException
    {
        if(bootsValidator.isValidate(boot))
        {
            bootsDao.createBoots(boot);
            return true;
        }

        return  false;
    }

    @Override
    public Boots getBootsById(Integer bootstId)
    {
        List<Boots> boots = getAllBoots();

        for(Boots boot : boots)
        {
            if(boot.getProduct().getId() == bootstId)
                return boot;
        }

        return null;
    }

    @Override
    public Boots getBootsByName(String bootsName)
    {
        List<Boots> boots = getAllBoots();

        for(Boots boot : boots)
        {
            if(boot.getProduct().getProductName().equals(bootsName))
                return boot;
        }

        return null;
    }

    @Override
    public void removeBoots(Integer productId)
    {
        bootsDao.deleteBootsByProductId(productId);
    }
}
