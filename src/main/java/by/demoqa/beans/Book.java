package by.demoqa.beans;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@Builder
public class Book {
    private long isbn;
    private String title;
    private String subTitle;
    private String author;
    private Date publishDate;
    private String publisher;
    private int pages;
    private String description;
    private String website;
}
