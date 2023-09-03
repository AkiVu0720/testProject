import ManagerList.ListBook;
import ManagerList.ListCategory;
import objectClass.Book;
import objectClass.Category;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Lybrary {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ListBook listBook = new ListBook();
        ListCategory listCategory = new ListCategory();
        runMenu(scanner, listBook,listCategory);
    }
    public static void menuLibrary(){
        System.out.println("===== QUẢN LÝ THƯ VIỆN =====");
        System.out.println("1. Quản lý Thể loại");
        System.out.println("2. Quản lý Sách");
        System.out.println("3. Thoát");
    }
    public static void runMenu(Scanner scanner, ListBook listBook, ListCategory listCategory){
        menuLibrary();
       try {
           byte choice = Byte.parseByte(scanner.nextLine());
           switch (choice){
               case 1:
                   listCategory.inputCategory();
                   break;
               case 2:
                   break;
               case 3:
                   System.exit(0);
                   break;
           }
       } catch (NumberFormatException numberFormatException){
           System.out.println("Lỗi định dạng đầu vào - runMenu");
       }
       catch (Exception e){
           System.out.println("Lỗi trong quá trình chạy menu quản lý thư viện");
       }
    };




}
