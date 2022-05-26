package com.example.bla;


public class ModelTable1 {
    String bookName;
    Integer bookView;
    public  ModelTable1(String bookName, Integer bookView){
        this.bookName=bookName;

        this.bookView=bookView;

    }
    public String getBookName(){
        return bookName;
    }

    public Integer getBookPrice(){
        return bookView;
    }


}

