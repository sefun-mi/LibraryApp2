package org.example;

import org.apache.log4j.*;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Book {

    static Logger log = Logger.getLogger(Book.class.getName());
    @Id
    private int id;

    @Column
    private String book_name;

    @Column
    public int book_count;

    Book(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }



}
