package api;

import entity.Cloth;

import java.util.List;

public interface ClothFacade
{
    String createCloth(Cloth cloth);

    List<Cloth> getAllCloths();
}
