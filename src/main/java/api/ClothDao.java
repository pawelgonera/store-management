package api;

import entity.Cloth;

import java.util.List;

public interface ClothDao
{
    void createCloth(Cloth cloth);

    void deleteClothById(Long clothId);

    void deleteClothByName(String clothName);

    Cloth getClothByName(String clothName);

    List<Cloth> getAllCloths();
}
