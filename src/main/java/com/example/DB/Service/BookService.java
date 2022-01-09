package com.example.DB.Service;

import com.example.DB.Model.Book;
import com.example.DB.Repository.DBOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class BookService {

    @Autowired
    DBOperations bookDB;

    public String createTable(String tableName) throws SQLException {
        return bookDB.createTable(tableName);
    }

    public String insertBook(Book book) throws SQLException {
        return bookDB.insertBook(book);
    }

    public List<Book> getAllBooks() throws SQLException {
        return bookDB.getAllBooks();
    }

    public Book getBookById(int id) throws SQLException {
        return bookDB.getBookById(id);
    }
}
