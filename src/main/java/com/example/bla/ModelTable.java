package com.example.bla;


public class ModelTable {
    String bookName,Username;
    Integer bookPrice;
    public  ModelTable(String bookName, Integer bookPrice, String Username){
        this.bookName=bookName;
        this.bookPrice=bookPrice;
        this.Username=Username;

    }
    public String getBookName(){
        return bookName;
    }

    public Integer getBookPrice(){
        return bookPrice;
    }

    public String getUsername(){
        return Username;
    }
}
