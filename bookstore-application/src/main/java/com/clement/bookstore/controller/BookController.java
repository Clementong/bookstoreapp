package com.clement.bookstore.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.clement.bookstore.model.Book;
import com.clement.bookstore.service.BookService;

import io.swagger.v3.oas.annotations.Hidden;

import org.slf4j.Logger;
import java.util.List;

@RestController
public class BookController {
    Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    BookService bookService;

    @Hidden
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public RedirectView  home(){
        logger.info("Loading home");
        return new RedirectView("swagger-ui/index.html#/");
    }

    @RequestMapping(value = "/retrieveAllRecords", method = RequestMethod.GET)
    public ResponseEntity<List<Book>>  retrieveAllRecords(){
        logger.info("Retrieving all records");
        List<Book> res = bookService.getAllBook();
        if(res != null) {
            logger.info("Number of books retrieved: "+res.size());
            return new ResponseEntity<List<Book>>(res, HttpStatus.OK);    
        }else{
            logger.info("Records does not exist");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    // searchAllRecordsByAuthor()
    @RequestMapping(value = "/searchBookByAuthor", method = RequestMethod.GET)
    public ResponseEntity<String>  searchBookByAuthor(@RequestParam("author") String author){
        logger.info("Retriving information for book whose is author: " + author);
        String res = bookService.getBookByAuthor(author);
        if (res == null) { 
            logger.info("Record does not exist");
            return new ResponseEntity<>("Record does not exist", HttpStatus.BAD_REQUEST);
        }else{ 
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
    }

    // searchAllRecordsByISBN()
    @RequestMapping(value = "/searchBookByISBN", method = RequestMethod.GET)
    public ResponseEntity<String>  searchBookByISBN(@RequestParam("ISBN") String ISBN){
        logger.info("Retriving information for book with ISBN: " + ISBN);
        String res = bookService.getBookByISBN(ISBN);
        if (res == null) { 
            logger.info("Record does not exist");
            return new ResponseEntity<>("Record does not exist", HttpStatus.BAD_REQUEST);
        }else{ 
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
    }

    // searchAllRecordsByTitle()
    @RequestMapping(value = "/searchBookByTitle", method = RequestMethod.GET)
    public ResponseEntity<String>  searchBookByTitle(@RequestParam("title") String title){
        logger.info("Retriving information for book with title: " + title);
        String res = bookService.getBookByTitle(title);
        if (res == null) { 
            logger.info("Record does not exist");
            return new ResponseEntity<>("Record does not exist", HttpStatus.BAD_REQUEST);
        }else{ 
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
    }
    


    @RequestMapping(value = "/retrieveBookQuantity", method = RequestMethod.GET)
    public ResponseEntity<String>  retrieveBookQuantityByISBN(@RequestParam("ISBN") String ISBN){
        logger.info("Retriving information for book with ISBN: " + ISBN);
        int res = bookService.getBookQuantityByISBN(ISBN);
        if (res == -1) { 
            logger.info("Record does not exist");
            return new ResponseEntity<>("Record does not exist", HttpStatus.BAD_REQUEST);
        }else{ 
            return new ResponseEntity<>(Integer.toString(res), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/updateBookQuantity",  headers="Content-Type=application/json", method = RequestMethod.PUT)
    public ResponseEntity<String>  updateBookQuantityByISBN(@RequestBody Book req){
        logger.info("Updating book quantity for ISBN: " + req.getISBN());
        int res = bookService.updateQuantity(req);
        if (res == 1) { 
            return new ResponseEntity<>("Book with ISBN: " + req.getISBN() + " quantity is updated successfully.", HttpStatus.OK);
        } else if (res == 0) {
            return new ResponseEntity<>("Book with ISBN: " + req.getISBN() + " quantity is not updated.", HttpStatus.NOT_FOUND);
        } else if (res == -1) {
            return new ResponseEntity<>("Exception occured while updating book with ISBN: " + req.getISBN(), HttpStatus.SERVICE_UNAVAILABLE);
        }else{ 
            logger.info("Case not handled, response: " + res);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/insertRecord",  headers="Content-Type=application/json", method = RequestMethod.POST)
    public ResponseEntity<String> insertRecordByISBN(@RequestBody Book req){
        logger.info("Creating record for book with ISBN: " + req.getISBN());
        int res = bookService.createRecord(req);
        if (res == 1) { 
            return new ResponseEntity<>("Book with ISBN: " + req.getISBN() + " is created successfully.", HttpStatus.OK);
        }else if (res == 0){
            return new ResponseEntity<>("Book with ISBN: " + req.getISBN() + " is not found.", HttpStatus.NOT_FOUND);
        }else if (res == -1){
            return new ResponseEntity<>("Exception occured while deleting ISBN: " + req.getISBN(), HttpStatus.SERVICE_UNAVAILABLE);
        }else{ 
            logger.info("Case not handled, response: " + res);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    } 

    @RequestMapping(value = "/deleteBookById",  headers="Content-Type=application/json", method = RequestMethod.DELETE)
    public ResponseEntity<String>  deleteBookByISBN(@RequestBody Book req){
        logger.info("Deleting book with ISBN: " + req.getISBN());
        int res = bookService.deleteRecord(req);
        if (res == 1) { 
            return new ResponseEntity<>("Book with ISBN: " + req.getISBN() + " is deleted successfully.", HttpStatus.OK);
        } else if (res == 0) {
            return new ResponseEntity<>("Book with ISBN: " + req.getISBN() + " is not found.", HttpStatus.NOT_FOUND);
        } else if (res == -1) {
            return new ResponseEntity<>("Exception occured while deleting ISBN: " + req.getISBN(), HttpStatus.SERVICE_UNAVAILABLE);
        }else{ 
            logger.info("Case not handled, response: " + res);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/searchByPriceRange", method = RequestMethod.GET)
    public ResponseEntity<String>  searchByPriceRange(@RequestParam("minimumPrice") String minimumPrice, @RequestParam("maximumPrice") String maximumPrice){
        logger.info(" Performing search using price range with minimum price as: " + minimumPrice + " and maximum price as " + maximumPrice);
        String res = bookService.getBooksByPriceRange(minimumPrice, maximumPrice);
        if (res == null) { 
            logger.info("Record does not exist");
            return new ResponseEntity<>("Record does not exist", HttpStatus.BAD_REQUEST);
        }else{ 
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
    }



}