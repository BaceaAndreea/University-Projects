package Controller;

import java.util.ArrayList;

public interface ControllerInterface<ObjectType> {
    public void add(ArrayList<String> newObjectData);
    public void delete(ArrayList<String> identifier);
    public void update(ArrayList<String> identifier, ArrayList<String> newObjectData);
    public ArrayList<ObjectType> readAll();
}