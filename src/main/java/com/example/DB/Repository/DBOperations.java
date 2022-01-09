package com.example.DB.Repository;


import com.example.DB.Model.Book;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Repository
public class DBOperations {

    private Connection connection;
    private String tableName = "my_books";

//    method to create a connection to database.
    public void getConnection() throws SQLException {
        if(connection == null) {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/books", "root", "Varshinideva12");
        }
    }

//    method to close the connection
    public void closeConnection() {
        if(connection != null)
            connection = null;
    }
    
    public String createTable(String tableName) throws SQLException {
        getConnection();

        this.tableName = tableName;
        System.out.println(tableName);
        Statement statement = connection.createStatement();
        boolean isOpsSuccess = statement.execute("CREATE TABLE "+tableName+"(id INT primary key AUTO_INCREMENT, name VARCHAR(30), authorName VARCHAR(30), cost INT)");

        String str;
        if(isOpsSuccess) {
            str = "Table "+tableName+" is successfully created";
            System.out.println(str);
        }
        else {
            str = "Table "+tableName+" is not successfully created";
            System.out.println(str);
        }

        closeConnection();
        return str;

    }

    public String insertBook(Book book) throws SQLException {
        getConnection();

        PreparedStatement prepareStatement = connection.prepareStatement("INSERT INTO "+this.tableName+" VALUES(NULL, ?, ?, ?)");
        prepareStatement.setString(1, book.getName());
        prepareStatement.setString(2, book.getAuthorName());
        prepareStatement.setInt(3, book.getCost());

        int row = prepareStatement.executeUpdate();
        String str;
        if(row < 1) {
            str = "Book is not inserted properly";
        }
        else {
            str = "Book is inserted";
        }
        closeConnection();
        return str;
    }

    public List<Book> getAllBooks() throws SQLException {
        getConnection();

        List<Book> books = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM "+this.tableName);

        while(resultSet.next()) {
            String bookName = resultSet.getString(2);
            String authorName = resultSet.getString(3);
            int cost = resultSet.getInt(4);

            books.add(new Book(bookName, authorName, cost));
        }
        closeConnection();
        return books;
    }

    public Book getBookById(int id) throws SQLException {
        System.out.println(id);
        getConnection();

        List<Book> books = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM "+this.tableName+" WHERE id = "+id);

        while(resultSet.next()) {
            String bookName = resultSet.getString(2);
            String authorName = resultSet.getString(3);
            int cost = resultSet.getInt(4);
            Book book = new Book(bookName, authorName, cost);

            closeConnection();
            return book;
        }
        System.out.println("No book with the given id is present in the database");
        closeConnection();
        return null;
    }
}
