package dataFile;

import objectClass.Category;

import java.io.*;
import java.util.ArrayList;

public class dataCategory {
    public static void writeDataCategory(ArrayList<Category> categoryArrayList) {
        //1 Khoi tao doi tuong file
        File file = new File("CategoryList.txt");
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(categoryArrayList);
            oos.flush();
        } catch (FileNotFoundException ex1) {
            System.err.println("Loi ghi du lieu vao  file");
        } catch (IOException ex2) {
            System.err.println("Loi khong ton tai file");
        } catch (Exception e) {
            System.err.println("Loi xay ra trong qua trinh ghi du lieu");
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException ex1) {
                System.err.println("Loi dong cac stream");
            } catch (Exception e) {
                System.err.println("Loi say ra trong qua trinh dong Stream");
            }
        }
    };
}
