/**
 * Lớp đối tượng thể loại sách.
 * Quản lý các thông tin về các thể loại sách.
 * Người tạo:  Vũ Anh Tuấn.
 */
package objectClass;
import interFace.IEntity;

import java.util.List;
import java.util.Scanner;

public class Category implements IEntity<Category> {
    //1 Attributes.
    private int categoryId;
    private String categoryName;
    private boolean categoryStatus;

    //2 Get, set

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public boolean isCategoryStatus() {
        return categoryStatus;
    }

    public void setCategoryStatus(boolean categoryStatus) {
        this.categoryStatus = categoryStatus;
    }

    //3. Constructor
    public Category() {
    }

    public Category(int categoryId, String categoryName, boolean categoryStatus) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryStatus = categoryStatus;
    }

    //4. input, output.
    @Override
    public void input(Scanner scanner, List<Category> arrayList) {
        this.categoryId = validateCategoryId(scanner,arrayList);
        this.categoryName = validateCategoryName(scanner,arrayList);
        this.categoryStatus = validateCategoryStatus(scanner);
    }

    @Override
    public void output() {
        String statusStr = this.categoryStatus?"Hoạt động":"Không hoạt động";
        System.out.printf("%3d \t\t %3s\t\t %3s",this.categoryId,this.categoryName,statusStr);
    }
//5. Business methods
    public int validateCategoryId(Scanner scanner, List<Category> arrayList) {
        boolean isExit = false;
        do {
        System.out.println("Nhập mã thể loại sách: ");
        String categoryIdStr = scanner.nextLine();
            if (validateNull(categoryIdStr)) {
                try {
                    int categoryId = Integer.parseInt(categoryIdStr);
                    boolean isCategoryId = arrayList.stream().anyMatch(category -> category.getCategoryId() == categoryId);
                    if (isCategoryId) {
                        System.out.println("Mã đã tồn tại");
                    } else {
                        isExit = true;
                        return categoryId;
                    }
                } catch (NumberFormatException numberFormatException) {
                    System.out.println("Lỗi định dạng mã sách.");
                } catch (NullPointerException nullPointerException) {
                    System.out.println("Lỗi mảng Category null - Nhập mã thể loại");
                } catch (Exception e) {
                    System.out.println("Lỗi xảy ra trong quá trình Nhập mã thể loại");
                }
            } else {
                System.out.println("Vui lòng không để trống.");
            }
        } while (!isExit);
        return -1;
    }

    ;

    public String validateCategoryName(Scanner scanner, List<Category> arrayList) {
        boolean isExit = false;
        do {
            System.out.println("Nhập tên thể loại:");
            String categoryNameStr = scanner.nextLine();
            if (validateNull(categoryNameStr)) {
                try {
                    boolean isCategoryId = arrayList.stream().anyMatch(category -> category.getCategoryName().equalsIgnoreCase(categoryNameStr));
                    if (isCategoryId) {
                        System.out.println("Tên đã tồn tại");
                    } else {
                        isExit = true;
                        return categoryNameStr;
                    }
                } catch (NullPointerException nullPointerException) {
                    System.out.println("Lỗi mảng Category null - Nhập tên thể loại.");
                } catch (Exception e) {
                    System.out.println("Lỗi xảy ra trong quá trình Nhập tên thể loại");
                }
            } else {
                System.out.println("Vui lòng không để trống.");
            }
        } while (!isExit);
        return null;
    };

    public boolean validateCategoryStatus(Scanner scanner) {
        System.out.println("Nhập trạng thái thể loại sách (true, false)");
        do {
            String str = scanner.nextLine();
            if (validateNull(str)) {
                if (str.trim().equalsIgnoreCase("true") || str.trim().equalsIgnoreCase("false")) {
                    return Boolean.parseBoolean(str);
                } else {
                    System.out.println("Vui lòng nhập kí tự: true hoặc false");
                }
            } else {
                System.out.println("vui Lòng Không Để Trống");
            }
        } while (true);
    };

    public boolean validateNull(String str) {
        if ( str.trim().length() != 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isOnlyCategory(int categoryId, List<Category>categoryList){
        return categoryList.stream().anyMatch(category -> category.getCategoryId()==categoryId);
    };

}
