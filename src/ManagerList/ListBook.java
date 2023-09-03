package ManagerList;

import objectClass.Book;

import java.util.ArrayList;
import java.util.List;

public class ListBook {
    private List<Book>bookList;

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public ListBook() {
        this.bookList =new ArrayList<Book>();
    }
}
