package by.demoqa.beans;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class User {
    private String name;
    private String lastName;
    private int age;
    private String email;
    private long salary;
    private String department;
}
