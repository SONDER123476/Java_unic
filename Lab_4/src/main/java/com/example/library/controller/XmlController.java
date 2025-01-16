package com.example.library.controller;

import com.example.library.model.Book;
import com.example.library.model.Author;
import com.example.library.repository.BookRepository;
import com.example.library.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;

import jakarta.servlet.http.HttpServletResponse;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

@RestController
@RequestMapping("/api/xml")
public class XmlController {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/authors")
    public ResponseEntity<String> getAuthorsXml(HttpServletResponse response) throws IOException {
        // Чтение XSLT файла
        ClassPathResource xslFile = new ClassPathResource("static/authors.xsl");
        String xslt = new String(FileCopyUtils.copyToByteArray(xslFile.getInputStream()));

        // Получение данных из базы данных
        List<Author> authors = authorRepository.findAll();

        // Логирование данных из базы данных
        for (Author author : authors) {
            System.out.println("Author: " + author.getFirstName() + " " + author.getLastName());
        }

        // Преобразование списка авторов в XML
        String authorsXml = convertAuthorsToXml(authors);

        // Применение XSLT к XML данным
        String transformedXml = applyXsltToXml(authorsXml, xslt);
        response.setHeader(HttpHeaders.CONTENT_TYPE, "application/xml");

        return ResponseEntity.ok(transformedXml);
    }

    @GetMapping("/books")
    public ResponseEntity<String> getBooksXml(HttpServletResponse response) throws IOException {
        // Чтение XSLT файла
        ClassPathResource xslFile = new ClassPathResource("static/books.xsl");
        String xslt = new String(FileCopyUtils.copyToByteArray(xslFile.getInputStream()));

        // Получение данных из базы данных
        List<Book> books = bookRepository.findAll();
        List<Author> authors = authorRepository.findAll();

        // Логирование данных
        for (Book book : books) {
            System.out.println("Book: " + book.getTitle() + ", AuthorId: " + book.getAuthor());
        }

        // Преобразование списка книг в XML
        String booksXml = convertBooksToXml(books, authors);

        // Применение XSLT к XML данным
        String transformedXml = applyXsltToXml(booksXml, xslt);

        // Установка заголовков ответа
        response.setHeader(HttpHeaders.CONTENT_TYPE, "application/xml");

        return ResponseEntity.ok(transformedXml);
    }

    private String convertAuthorsToXml(List<Author> authors) {
        // Преобразуем список авторов в XML формат. Для простоты используем StringBuilder.
        StringBuilder xmlBuilder = new StringBuilder();
        xmlBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        xmlBuilder.append("<authors>");

        for (Author author : authors) {
            xmlBuilder.append("<author>");
            xmlBuilder.append("<id>").append(author.getId()).append("</id>");
            xmlBuilder.append("<firstName>").append(author.getFirstName()).append("</firstName>");
            xmlBuilder.append("<lastName>").append(author.getLastName()).append("</lastName>");
            xmlBuilder.append("<email>").append(author.getEmail()).append("</email>");
            xmlBuilder.append("</author>");
        }

        xmlBuilder.append("</authors>");

        return xmlBuilder.toString();
    }

    private String convertBooksToXml(List<Book> books, List<Author> authors) {
        // Преобразуем список книг в XML формат
        StringBuilder xmlBuilder = new StringBuilder();
        xmlBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        xmlBuilder.append("<books>");

        for (Book book : books) {
            xmlBuilder.append("<book>");
            xmlBuilder.append("<id>").append(book.getId()).append("</id>");
            xmlBuilder.append("<title>").append(book.getTitle()).append("</title>");
            xmlBuilder.append("<isbn>").append(book.getIsbn()).append("</isbn>");

            String authorFirstName = findAuthorFirstNameById(book.getAuthor(), authors);
            String authorLastName = findAuthorLastNameById(book.getAuthor(), authors);
            xmlBuilder.append("<author>");
            xmlBuilder.append("<firstName>").append(authorFirstName).append("</firstName>");
            xmlBuilder.append("<lastName>").append(authorLastName).append("</lastName>");
            xmlBuilder.append("</author>");
            xmlBuilder.append("</book>");
        }

        xmlBuilder.append("</books>");

        return xmlBuilder.toString();
    }

    private String findAuthorFirstNameById(Long authorId, List<Author> authors) {
        for (Author author : authors) {
            if (author.getId().equals(authorId)) {
                return author.getFirstName();
            }
        }
        return "Unknown"; // Если автора не нашли
    }

    private String findAuthorLastNameById(Long authorId, List<Author> authors) {
        for (Author author : authors) {
            if (author.getId().equals(authorId)) {
                return author.getLastName();
            }
        }
        return "Unknown"; // Если автора не нашли
    }

    private String applyXsltToXml(String xml, String xslt) throws IOException {
        try {
            // Применяем XSLT преобразование
            TransformerFactory factory = TransformerFactory.newInstance();
            StreamSource xslStream = new StreamSource(new ByteArrayInputStream(xslt.getBytes()));
            javax.xml.transform.Transformer transformer = factory.newTransformer(xslStream);

            StreamSource xmlStream = new StreamSource(new ByteArrayInputStream(xml.getBytes()));
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);

            transformer.transform(xmlStream, result);

            return writer.toString();
        } catch (Exception e) {
            throw new IOException("Error during XSLT transformation", e);
        }
    }
}
