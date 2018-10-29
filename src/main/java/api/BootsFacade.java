package api;

import entity.Boots;

import java.util.List;

public interface BootsFacade
{
    String createBoots(Boots boots);

    List<Boots> getAllBoots();
}
