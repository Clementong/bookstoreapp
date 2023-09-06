package com.clement.bookstore.repository;
import java.util.List;

public interface RepositoryInf {

    List<?> retrieveAll(); 
    List<?> getBooksByPriceRange(String minimumPrice, String maximumPrice); 


    Object getBookByISBN(String ISBN);
    Object getBookByAuthor(String author);
    Object getBookByTitle(String title);

    int updateQuantity(String ISBN, int quantity); 
    int delete(String ISBN);
    // int insert(String title, String author, String ISBN, float price, int quantity);

}
