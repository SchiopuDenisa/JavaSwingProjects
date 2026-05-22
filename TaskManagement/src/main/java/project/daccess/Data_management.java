package project.daccess;

import java.io.*;

public class Data_management {

    public static void serialize(Object data, String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <T> T deserialize(String filename) {
        T data = null;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            data = (T) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return data;
    }
}
