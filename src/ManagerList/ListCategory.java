package ManagerList;

import objectClass.Book;
import objectClass.Category;

import java.util.*;
import java.util.stream.Collectors;

public class ListCategory {
    private List<Category>categoryList;
    private ListBook listBook;
    private  Category category;

    public ListBook getListBook() {
        return listBook;
    }

    public void setListBook(ListBook listBook) {
        this.listBook = listBook;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }
//3 Constructors
    public ListCategory() {
        this.categoryList = new ArrayList<Category>();
    }
    //4 Input, output
    public  void menuCategory(){
        System.out.println("===== QUẢN LÝ THỂ LOẠI =====");
        System.out.println("1. Thêm mới thể loại");
        System.out.println("2. Hiển thị danh sách theo tên A–Z");
        System.out.println("3. Thống kê thể loại và số sách có trong mỗi thể loại");
        System.out.println("4. Cập nhật thể loại");
        System.out.println("5. Xóa thể loại");
        System.out.println("6. Quay lại");
    }
    public void inputCategory(){
        Scanner scanner = new Scanner(System.in);
        do {
            menuCategory();
            byte choice = Byte.parseByte(scanner.nextLine());
            switch (choice){
                case 1:
                    addList(scanner);
                    break;
                case 2:
                    showListA_Z();
                    break;
                case 3:
                    numberCatagoryAndNumberBook();
                    break;
                case 4:
                    updateCategory(scanner);
                    break;
                case 5:
                    deleteCategory(scanner);
                    break;
                case 6: break;
            }
        }while (true);
    }public void addList(Scanner scanner){
        Category category = new Category();
        category.input(scanner,this.categoryList);
        categoryList.add(category);
    }
    public void showListA_Z(){
        this.getCategoryList().stream()
                .sorted(Comparator.comparing(Category::getCategoryName))
                .forEach(category1 ->{
                    System.out.println("\n");
                    category1.output();
                        });

    };
    public void numberCatagoryAndNumberBook(){
        long quantity = this.getCategoryList().stream().count();
        System.out.println("Thống kê thể loại: " + quantity + " loại");
        List<Book>bookList = this.listBook.getBookList();
        for (Category category1 : this.getCategoryList()) {
            int number = 0;
            for (Book book: bookList) {
                if (book.getCategoryId()==category1.getCategoryId()){
                    number++;
                }
            }
            System.out.println(category1.getCategoryName() +"- "+ number + "cuốn sách");
        }
    }
    public void updateCategory(Scanner scanner){
        showListA_Z();
        System.out.println("Nhập id cần cập nhập: ");
        String categoryIdUpdate = scanner.nextLine();
        if (!category.validateNull(categoryIdUpdate)){
            try {
                int categoryId = Integer.parseInt(categoryIdUpdate);
                this.categoryList.stream()
                        .filter(category1 -> category1.getCategoryId()==categoryId)
                        .forEach(category1 ->
                                category1.setCategoryName(category1.validateCategoryName(scanner,this.categoryList)));
            } catch (NumberFormatException numberFormatException){
                System.out.println("Lỗi định dạng dữ liệu - updateCategory");
            }
            catch (Exception e){
                System.out.println("Loi trong qua trinh cap nhap listCategory");
            }

        }else {
            System.out.println("Không được bỏ trống");
        }

    }
    public void deleteCategory(Scanner scanner){
        showListA_Z();
        categoryStatus();
        System.out.println("Nhập mã id cần xóa.");
        String deleteIdStr = scanner.nextLine();
            if (!category.validateNull(deleteIdStr)){
                try {
                    int deleteId = Integer.parseInt(deleteIdStr);
                    boolean isOnly = category.isOnlyCategory(deleteId,this.categoryList);
                    if (isOnly){
                        for (Category cate: this.categoryList) {
                            if (cate.isCategoryStatus()){
                                System.out.println("Thể loại này đang tồn tại sách.");
                            }else {
                                if (cate.getCategoryId()==deleteId){
                                    this.categoryList.remove(cate);
                                    System.out.println("Đã xóa thành công.");
                                }
                            }
                        }
                    }else {
                        System.out.println("Mã id không tồn tại.");
                    }
                } catch (NumberFormatException numberFormatException){
                    System.out.println("Lỗi định dạng dữ liệu - updateCategory");
                }
                catch (Exception e){
                    System.out.println("Loi trong qua trinh cap nhap listCategory");
                }
            }else {
                System.out.println("Không được bỏ trống");
            }

    };

public  void categoryStatus(){
    for (Category category1:this.categoryList) {
        int numberCategory = 0;
        for (Book book : this.listBook.getBookList()){
            if (category1.getCategoryId()==book.getCategoryId()){
                numberCategory++;
            }
        }
        boolean isCategoryStatus = numberCategory == 0 ? false:true;
       category1.setCategoryStatus(isCategoryStatus);
    };
};
    public void showList_Id_Name (){
        Map<Integer,String> listCategoryKey = this.categoryList.stream()
                .collect(Collectors.toMap(Category::getCategoryId, Category::getCategoryName));
        System.out.println("CategoryId - CategoryName");
        listCategoryKey.forEach((key, value)->
                System.out.println(key +"-"+ value));
    }
}
