package com.polarbookshop.catalogservice.web;

import com.polarbookshop.catalogservice.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.data.annotation.Id;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class BookJsonTests {
    @Autowired
    private JacksonTester<Book> json;

    @Test
    void testSerialize() throws Exception {
        var book = Book.of("1234567890", "Title", "Author", 9.90, "Polarsophia");
        var jsonContent = json.write(book);
        System.out.println(jsonContent);
        assertThat(jsonContent).extractingJsonPathNumberValue("@.Id").isEqualTo(book.id());
        assertThat(jsonContent).extractingJsonPathStringValue("@.isbn").isEqualTo(book.isbn());
        assertThat(jsonContent).extractingJsonPathStringValue("@.title").isEqualTo(book.title());
        assertThat(jsonContent).extractingJsonPathStringValue("@.author").isEqualTo(book.author());
        assertThat(jsonContent).extractingJsonPathNumberValue("@.price").isEqualTo(book.price());
        assertThat(jsonContent).extractingJsonPathNumberValue("@.createdDate").isEqualTo(book.createdDate());
        assertThat(jsonContent).extractingJsonPathNumberValue("@.lastModifiedDate").isEqualTo(book.lastModifiedDate());
        assertThat(jsonContent).extractingJsonPathNumberValue("@.version").isEqualTo(book.version());
    }

    @Test
    void testDeserialize() throws Exception {
        var content = """
                {
                    "isbn": "1234567890",
                    "title": "Title",
                    "author": "Author",
                    "price": "9.90",
                    "publisher": "Polarsophia"
                }
                """;

        assertThat(json.parse(content))
                .usingRecursiveComparison()
                .isEqualTo(Book.of("1234567890", "Title", "Author", 9.90, "Polarsophia"));
    }
}
