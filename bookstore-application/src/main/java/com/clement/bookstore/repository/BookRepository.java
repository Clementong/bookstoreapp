package com.clement.bookstore.repository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.clement.bookstore.model.Book;
import com.clement.bookstore.model.BookMapper;

@Repository
public class BookRepository implements RepositoryInf {
    Logger logger = LoggerFactory.getLogger(BookRepository.class);
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Book> retrieveAll(){
        try{
            return jdbcTemplate.query("SELECT * from bookstore.books", new BookMapper());
        }catch(DataAccessException ex){
            logger.error("Error retriving all records", ex);
            return null;
        }
         
    };

    @Override
    public Book getBookByISBN(String ISBN){
        try{
            return jdbcTemplate.queryForObject("SELECT * from bookstore.books where ISBN=?", new BookMapper(), new Object[] { ISBN });
        }catch(DataAccessException ex){
            logger.error("Error retriving all records", ex);
            return null;
        }

    };

    @Override
    public List<Book> getBookByAuthor(String author){
        try{
            return jdbcTemplate.query("SELECT * from bookstore.books where author ILIKE '%" + author.toLowerCase() + "%';", new BookMapper());
        }catch(DataAccessException ex){
            logger.error("Error retriving books with author: "+author, ex);
            return null;
        }

    };

    @Override
    public List<Book> getBookByTitle(String title){
        try{
            return jdbcTemplate.query("SELECT * from bookstore.books where title ilike '%" +  title.toLowerCase() + "%';", new BookMapper());
        }catch(DataAccessException ex){
            logger.error("Error retriving books with author: "+title, ex);
            return null;
        }

    };
   
    @Override
    public List<Book> getBooksByPriceRange(String minimumPrice, String maximumPrice ){
        try{
            return jdbcTemplate.query("SELECT * from bookstore.books where " +  minimumPrice +  "<= price and price <=" + maximumPrice + ";" , new BookMapper());
        }catch(DataAccessException ex){
            logger.error("Error retriving books with price range of minimum price: " + minimumPrice + " and maxmium price: " + maximumPrice, ex);
            return null;
        }

    };


    @Override
    public int updateQuantity(String ISBN, int quantity){
        try{
            return jdbcTemplate.update("Update bookstore.books SET quantity=? WHERE ISBN=?",quantity,ISBN); 
        }catch(DataAccessException ex){
            logger.error("Error inserting record for book with ISBN: " + ISBN, ex);
            return -1;
        }
    };

    @Override
    public int delete(String ISBN){
        try{
            return jdbcTemplate.update("DELETE FROM bookstore.books WHERE ISBN=?", ISBN);
        }catch(DataAccessException ex){
            logger.error("Error deleting book with ISBN: " + ISBN, ex);
            return -1;
        }
            
    };


    public int insert(Book book){
        try{
            return jdbcTemplate.update("Insert into bookstore.books (title, author, ISBN, price, quantity ) values (?,?,?,?,?)", book.getTitle(),book.getAuthor(), book.getISBN(), book.getPrice(), book.getQuantity()); 
        }catch(DataAccessException ex){
            logger.error("Error inserting record for book with ISBN: " + book.getISBN(), ex);
            return -1;
        }
    };
    

}
