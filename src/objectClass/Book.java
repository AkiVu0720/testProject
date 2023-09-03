/**
 * Lớp đối tượng sách.
 * Quản lý các thông tin về sách.
 * Người tạo:  Vũ Anh Tuấn.
 */
package objectClass;

import interFace.IEntity;

import java.time.Year;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Book implements IEntity<Book> {
    static final double LENGTH_MIN_ID = 0;
    static final double LENGTH_MAX_ID = 4;
    static final double LENGTH_MIN_TITLE = 6;
    static final double LENGTH_MAX_TITLE = 50;
    static  final double YEAR_MIN = 1970;
    //1. Attributes
    private String bookId;
    private String title;
    private String author;
    private String publisher;
    private int year;
    private String description;
    private int categoryId;

    //2. Set,get
    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {this.bookId = bookId;};

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    //3. Constructor
    public Book() {
    }

    public Book(String bookId, String title, String author, String publisher, int year, String description, int categoryId) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.year = year;
        this.description = description;
        this.categoryId = categoryId;
    }

    //4. Input, output

    @Override
    public void input(Scanner scanner, List<Book> arrayList) {

    }

    @Override
    public void output() {

    }

    //5. Business methods.
    public  String validateBookId(Scanner scanner, List<Book> bookList) {
        do {
            System.out.println("Nhập Mã sách (Y/C: B***)");
            String bookIdStr = scanner.nextLine();
            byte isError = 0;
            if (isStrNull(bookId)) {
                System.out.println("Không được để trống.");
                isError++;
            }
            if (!isLength(bookId, LENGTH_MIN_ID, LENGTH_MAX_ID) || !bookIdStr.startsWith("B")) {
                System.out.println("Yêu cầu nhập đúng định dạng.");
                isError++;
            }
            if (isOnly(bookId, bookList)) {
                System.out.println("Mã đã tồn tại");
                isError++;
            }
            if (isError == 0) {
               return  bookIdStr;
            }

        } while (true);
    };
    public String validateBookTitle(Scanner scanner, List<Book> bookList){
        do {
            System.out.println("Nhập tiêu đề sách (6-50 kí tự)");
            String bookTitle = scanner.nextLine();
            byte isError = 0;
            if (isStrNull(bookTitle)){
                System.out.println("Không được để trống.");
                isError++;
            }
            if (!isLength(bookTitle,LENGTH_MIN_TITLE,LENGTH_MAX_TITLE)){
                System.out.println("Vui lòng nhập đúng định dạng.");
                isError++;
            }
            if (isOnly(bookTitle,bookList)){
                System.out.println("Mã đã tồn tại");
                isError++;
            }
            if (isError == 0){
                return bookTitle;
            }

        }while (true);
    };
    public String validateAuthor(Scanner scanner){
        do {
            System.out.println("Tên tác giả: ");
            String authorStr = scanner.nextLine();
            byte isError =0;
            if (isStrNull(authorStr)){
                System.out.println("Không được để trống.");
                isError++;
            }
            if (isError==0){
                return  authorStr;
            }
        }while (true);

    }
    public String validatePublisher(Scanner scanner){
        do {
            System.out.println("Nhà xuất bản: ");
            String authorStr = scanner.nextLine();
            byte isError =0;
            if (isStrNull(authorStr)){
                System.out.println("Không được để trống.");
                isError++;
            }
            if (isError==0){
                return  authorStr;
            }
        }while (true);

    }
    public int validateYear(Scanner scanner){
      do {
          byte isError = 0;
          System.out.println("Năm xuất bản: ");
          try {
              String yearStr = scanner.nextLine();
              if(isStrNull(yearStr)){
                  System.out.println("Không được để trống.");
                  isError ++;
              }
              Year yearCurrent = Year.now();
              int yearInt = Integer.parseInt(yearStr);
              if (yearInt >=YEAR_MIN && yearInt<=yearCurrent.getValue()){
                  return yearInt;
              }else {
                  System.out.printf("Yêu cầu từ năm %f đến %s", YEAR_MIN,yearCurrent);
              }



          } catch (NumberFormatException numberFormatException){
              System.out.println("Lỗi kiểu dữ liệu đầu vào - Year");
          }
          catch (Exception e){
              System.out.println("Lỗi xảy ra trong quá trình validateYear");
          };
      }while (true);
    };
    public String validateDescription(Scanner scanner){
        do {
        System.out.println("Nhập mô tả sách: ");
        String description = scanner.nextLine();

       if(!isStrNull(description)){
           return  description;
       }else {
           System.out.println("Không được để trống.");
       }
        }while (true);
    };
    public int validateCategoryIdOfBook(List<Category> tList) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Danh muc:");
        Map<Integer,String> listCategoryKey = tList.stream()
                .collect(Collectors.toMap(Category::getCategoryId, Category::getCategoryName));
        System.out.println("CategoryId - CategoryName");
        listCategoryKey.forEach((key, value)->
                        System.out.println(key +"-"+ value));
        do {
            System.out.println("Chọn danh mục: ");
            int categoryIdOfBook = Integer.parseInt(scanner.nextLine());

            for (Category cate: tList) {
                if (cate.getCategoryId()==categoryIdOfBook){
                    return categoryIdOfBook;
                }else {
                    System.out.println("Ma ban chon khong ton tai. Vui long chon lai.");
                }
            }
        }while (true);
    }
    public  boolean isOnly(String str, List<Book> bookList) {
        return bookList.stream()
                .anyMatch(book -> book.getBookId()
                        .equalsIgnoreCase(str));
    };

    public  boolean isLength(String str, double min, double max) {
        if (min == LENGTH_MIN_ID && max == LENGTH_MAX_ID) {
            return str.length() == LENGTH_MAX_ID ? true : false;
        } else {
            return (str.length() >= min && str.length() <= max) ? true : false;
        }
    };

    public boolean isStrNull(String str) {
        return str==null || str.isEmpty();
    };

}
