package com.example.library.service;

import com.example.library.model.Book;
import com.example.library.model.ChangeLog;
import com.example.library.model.ChangeLogEvent;
import com.example.library.repository.BookRepository;
import com.example.library.repository.ChangeLogRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private final BookRepository bookRepository;
    @Autowired
    private final ChangeLogRepository changeLogRepository;
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public BookService(BookRepository bookRepository, ChangeLogRepository changeLogRepository) {
        this.bookRepository = bookRepository;
        this.changeLogRepository = changeLogRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book saveBook(Book book) {
        boolean isNew = book.getId() == null;
        Book savedBook = bookRepository.save(book);
        if (isNew) {
            logChange("CREATE", "Book", savedBook.getId(), "Created new book with title: " + savedBook.getTitle());
            eventPublisher.publishEvent(new ChangeLogEvent(this,
                    "Book",
                    "CREATE",
                    "New book added: " + book.getTitle()));
        } else {
            logChange("UPDATE", "Book", savedBook.getId(), "Updated book with title: " + savedBook.getTitle());
            eventPublisher.publishEvent(new ChangeLogEvent(this,
                    "Book",
                    "UPDATE",
                    "Update book added: " + book.getTitle()));
        }
        return savedBook;
    }

    public void deleteBookById(Long id) {
        bookRepository.findById(id).ifPresent(book -> {
            bookRepository.delete(book);
            logChange("DELETE", "Book", book.getId(), "Deleted book with title: " + book.getTitle());
        });
    }

    private void logChange(String changeType, String entityName, Long entityId, String description) {
        ChangeLog changeLog = new ChangeLog();
        changeLog.setChangeType(changeType);
        changeLog.setEntityName(entityName);
        changeLog.setEntityId(entityId);
        changeLog.setChangeDescription(description);
        changeLogRepository.save(changeLog);
    }
}
