package effective.software.testing.code.ch5;

import lombok.Getter;

@Getter
public class Book {
    private final String title;
    private final String author;
    private final int qtyOfPages;

    public Book(String title, String author, int qtyOfPages) {
        this.title = title;
        this.author = author;
        this.qtyOfPages = qtyOfPages;
    }
}
