package by.demoqa.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Productline {
    private String productLine;
    private String textDescription;
    private String htmlDescription;
    private String image;
}
