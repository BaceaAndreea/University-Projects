package Repository;
import Domain.*;

import java.util.ArrayList;

public interface RepositoryInterface<ObjectType>{
    void add(ObjectType newObject);
    void delete(ObjectType deletedObject);
    void update(ObjectType oldObject, ObjectType newObject);
    ArrayList<ObjectType> readAll();
    ObjectType findByIdentifier(ArrayList <String> identifier);
}