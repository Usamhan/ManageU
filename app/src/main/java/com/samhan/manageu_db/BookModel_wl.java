package com.samhan.manageu_db;

public class BookModel_wl {
   public String bookName,writerName;

    public BookModel_wl() {

    }

    public BookModel_wl(String bookName, String writerName) {
        this.bookName = bookName;
        this.writerName = writerName;

    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getWriterName() {
        return writerName;
    }

    public void setWriterName(String writerName) {
        this.writerName = writerName;
    }
}
