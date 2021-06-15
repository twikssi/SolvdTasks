package by.demoqa.beans;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class BookProfile {
    private String image;
    private String title;
    private String author;
    private String publisher;
    private String action;
}
