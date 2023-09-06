package com.clement.bookstore.model;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


public class BookMapper implements RowMapper<Book>{
    public Book mapRow(ResultSet resultSet, int i) throws SQLException{
        Book book = new Book(resultSet.getString("title")
                    ,resultSet.getString("author")
                    ,resultSet.getString("ISBN")
                    ,resultSet.getFloat("price")
                    ,resultSet.getInt("quantity"));
        return book;
    }
}
