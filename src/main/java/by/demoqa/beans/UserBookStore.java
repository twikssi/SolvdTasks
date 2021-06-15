package by.demoqa.beans;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class UserBookStore {
    private String id;
    private String name;
    private String password;
    private String token;
}
