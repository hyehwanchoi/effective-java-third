package chapter2.item3;

import java.io.*;

public class Serializable {

    public byte[] serializable(Object instance) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(instance);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bos.toByteArray();
    }

    public Object deserializable(byte[] serializedData) {
        ByteArrayInputStream bis = new ByteArrayInputStream(serializedData);

        try {
            ObjectInputStream ois = new ObjectInputStream(bis);
            return ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
