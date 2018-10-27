package api;

import entity.Boots;

import java.util.List;

public interface BootsFacade
{
    String createBoots(Boots boots);

    String removeBoots(String bootsName);

    List<Boots> getAllBoots();
}
