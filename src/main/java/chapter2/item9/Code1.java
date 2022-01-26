package chapter2.item9;

import java.io.*;

// try~finally 보다는 try~with~resources를 사용하라.
public class Code1 {

    public StringBuilder readFile(String dir) {

        StringBuilder stringBuilder = new StringBuilder();
        try (
                FileInputStream is = new FileInputStream(dir);
                BufferedInputStream bis = new BufferedInputStream(is)
        ) {
            int data;
            while ((data = bis.read()) != -1) {
                stringBuilder.append(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuilder;
    }
}
