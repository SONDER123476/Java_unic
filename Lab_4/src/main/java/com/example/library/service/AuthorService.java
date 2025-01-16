package com.example.library.service;

import com.example.library.model.Author;
import com.example.library.model.ChangeLog;
import com.example.library.model.ChangeLogEvent;
import com.example.library.repository.AuthorRepository;
import com.example.library.repository.ChangeLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    @Autowired
    private final AuthorRepository authorRepository;
    @Autowired
    private final ChangeLogRepository changeLogRepository;
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public AuthorService(AuthorRepository authorRepository, ChangeLogRepository changeLogRepository) {
        this.authorRepository = authorRepository;
        this.changeLogRepository = changeLogRepository;
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author saveAuthor(Author author) {
        boolean isNew = author.getId() == null;
        Author savedAuthor = authorRepository.save(author);
        if (isNew) {
            logChange("CREATE", "Author", savedAuthor.getId(),
                    "Added new author: " + savedAuthor.getFirstName() + " " + savedAuthor.getLastName());
            eventPublisher.publishEvent(new ChangeLogEvent(this,
                    "Author",
                    "CREATE",
                    "New author added: " + author.getFirstName() + " " + author.getLastName()));
        } else {
            logChange("UPDATE", "Author", savedAuthor.getId(),
                    "Updated author: " + savedAuthor.getFirstName() + " " + savedAuthor.getLastName());
            eventPublisher.publishEvent(new ChangeLogEvent(this,
                    "Author",
                    "UPDATE",
                    "Update author added: " + author.getFirstName() + " " + author.getLastName()));
        }
        return savedAuthor;
    }

    public void deleteAuthorById(Long id) {
        authorRepository.deleteById(id);
        logChange("DELETE", "Author", id, "Deleted author with ID: " + id);
        eventPublisher.publishEvent(new ChangeLogEvent(this,
                "Author",
                "DELETE",
                "Author deleted with ID: " + id));
    }

    private void logChange(String changeType, String entityName, Long entityId, String description) {
        ChangeLog log = new ChangeLog();
        log.setChangeType(changeType);
        log.setEntityName(entityName);
        log.setEntityId(entityId);
        log.setChangeDescription(description);
        changeLogRepository.save(log);
    }
}
