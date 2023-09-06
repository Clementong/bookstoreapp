package com.clement.bookstore.service;

import com.clement.bookstore.repository.BookRepository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.clement.bookstore.model.Book;
// Controller > Service > Repository > database 

@Service
public class BookService {
    Logger logger = LoggerFactory.getLogger(BookService.class);
    
    @Autowired
    BookRepository bookRepository;

    // CRUD 
    public List<Book> getAllBook(){
        List<Book> bookList = bookRepository.retrieveAll();
        return bookList;
    }

    public int getBookQuantityByISBN(String ISBN){
        Book res = bookRepository.getBookByISBN(ISBN);
        if(res == null){
            return -1;
        } else {
            return res.getQuantity();
        }
    }

    public int updateQuantity(Book book){
        int res = bookRepository.updateQuantity(book.getISBN(), book.getQuantity());
        if(res == -1 ){
            logger.info("Created record for book with ISBN: " + book.getISBN() + " with exception ");
        }else if(res == 0){
            logger.info("Created record for book with ISBN: " + book.getISBN() + " with response: " + res);
        }else{
            logger.info("Created record for book with ISBN: " + book.getISBN() + " with response: " + res);
        }
        return res;
    }

    public int createRecord(Book book){
        int res = bookRepository.insert(book);
        if(res == -1 ){
            logger.info("Created record for book with ISBN: " + book.getISBN() + " with exception ");
        }else if(res == 0){
            logger.info("Created record for book with ISBN: " + book.getISBN() + " with response: " + res);
        }else{
            logger.info("Created record for book with ISBN: " + book.getISBN() + " with response: " + res);
        }
        return res;

    }

    public int deleteRecord(Book book){
        int res = bookRepository.delete( book.getISBN());
        if(res == -1 ){
            logger.info("Deleted book with ISBN: " +  book.getISBN() + " with exception ");
        }else if(res == 0){
            logger.info("Deleted book with ISBN: " +  book.getISBN() + " with response: " + res);
        }else{
            logger.info("Deleted book with ISBN: " +  book.getISBN() + " with response: " + res);
        }

        return res;
    }

    // Search 
    public String getBookByAuthor(String author){
        List<Book> res = bookRepository.getBookByAuthor(author);
        if(res != null){
            return res.toString();
        } else {
            return null;
        }
    }

    public String getBookByISBN(String ISBN){
        Book res = bookRepository.getBookByISBN(ISBN);
        if(res != null){
            return res.toString();
        } else {
            return null;
        }
    }

    // book series 
    public String getBookByTitle(String title){
        List<Book> res = bookRepository.getBookByTitle(title);
        if(res != null){
            return res.toString();
        } else {
            return null;
        }
    }

    // book series 
    public String getBooksByPriceRange(String minimumPrice, String maximumPrice ){
        List<Book> res = bookRepository.getBooksByPriceRange(minimumPrice, maximumPrice );
        if(res != null){
            return res.toString();
        } else {
            return null;
        }
    }

}
