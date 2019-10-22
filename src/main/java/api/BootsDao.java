package api;

import entity.Boots;
import java.util.List;

public interface BootsDao
{
    void createBoots(Boots boots);

    void deleteBootsById(Long bootsId);

    void deleteBootsByProductId(Integer productId);

    List<Boots> getAllBoots();
}
