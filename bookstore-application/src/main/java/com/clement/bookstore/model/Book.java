package com.clement.bookstore.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Book {
    
    Logger logger = LoggerFactory.getLogger(Book.class);

    private String title;
    private String author;
    private String ISBN;  // 978-0-545-01022-1
    private float price;
    private Integer quantity;

    // public Book(){}

    public Book(String title, String author, String ISBN, float price, Integer quantity ){
        
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.price = price;
        this.quantity = quantity;
    }
    
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getISBN() {
        return this.ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public float getPrice() {
        return this.price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString(){
        return "Book Information Title: " + this.getTitle() 
                + " Author: " + this.getAuthor() 
                + " ISBN: " + this.getISBN()
                + " Price: " + this.getPrice()
                + " Quantity: " + this.getQuantity();
    }
    
}
