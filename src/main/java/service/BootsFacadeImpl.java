package service;

import entity.Boots;
import exception.BootsSkinTypeValidFormatException;
import java.util.Collections;
import java.util.List;

public class BootsFacadeImpl implements api.BootsFacade
{

    BootsServiceImpl bootsService = BootsServiceImpl.getInstance();

    private static BootsFacadeImpl instance = null;

    public BootsFacadeImpl()
    {

    }

    public static BootsFacadeImpl getInstance()
    {
        if(instance == null)
            instance = new BootsFacadeImpl();

        return instance;
    }

    @Override
    public String createBoots(Boots boots)
    {
        try
        {
            bootsService.saveBoots(boots);
        }catch (BootsSkinTypeValidFormatException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }

        return boots.getProduct().getProductName() + " zosta(ł/a) poprawnie stworzon(y/a)";
    }

    @Override
    public List<Boots> getAllBoots()
    {
        try
        {
            return bootsService.getAllBoots();
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }
}
