package interfaces;
import models.Endangered;
public interface EndangeredInterface {
    //save
    void save() throws Exception;
    void update(String name);

    //delete
    void delete();
}
