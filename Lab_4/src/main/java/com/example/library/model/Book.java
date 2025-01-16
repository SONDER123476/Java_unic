package com.example.library.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "books")
@XmlRootElement(name = "book")
public class Book {

    @Id
    private Long id;
    private String title;
    private Long author_id;
    private String isbn;

    @XmlElement
    public Long getId() {
        return id;
    }

    @XmlElement
    public void setId(Long id) {
        this.id = id;
    }

    @XmlElement
    public String getTitle() {
        return title;
    }

    @XmlElement
    public void setTitle(String title) {
        this.title = title;
    }

    @XmlElement
    public Long getAuthor() {
        return author_id;
    }

    @XmlElement
    public void setAuthor(Long author) {
        this.author_id = author;
    }

    @XmlElement
    public String getIsbn() {
        return isbn;
    }

    @XmlElement
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
