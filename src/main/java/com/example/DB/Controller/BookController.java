package com.example.DB.Controller;


import com.example.DB.Model.Book;
import com.example.DB.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("/createTable/{tableName}")
    public String createTable(@PathVariable String tableName) throws SQLException {
        return bookService.createTable(tableName);
    }

    @PostMapping("/insertBook")
    public String insertBook(@RequestBody Book book) throws SQLException {
        return bookService.insertBook(book);
    }

    @GetMapping("getAllBooks")
    public List<Book> getAllBooks() throws SQLException {
        return bookService.getAllBooks();
    }

    @GetMapping("/getBookById/{id}")
    public Book getBookById(@PathVariable int id) throws SQLException {
        return bookService.getBookById(id);
    }

}
