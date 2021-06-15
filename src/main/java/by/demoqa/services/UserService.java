package by.demoqa.services;

import by.demoqa.beans.User;
import by.demoqa.web.components.UserItem;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    public static User mapUser(UserItem usersItem) {
        return User.builder().
                name(usersItem.getNameCell()).
                lastName(usersItem.getLastNameCell()).
                age(Integer.parseInt(usersItem.getAgeCell())).
                email(usersItem.getEmailCell()).
                salary(Long.parseLong(usersItem.getSalaryCell())).
                department(usersItem.getDepartmentCell()).
                build();
    }

    public static int findIndexUser(List<User> userList, User user) {
        return userList.indexOf(user);
    }

    public static List<User> mapUserItemListToUserList(List<UserItem> listUserItems) {
        List<User> usersList = new ArrayList<>();
        for (UserItem usersItem : listUserItems) {
            if (!usersItem.getNameCell().equals(" "))
                usersList.add(User.builder().
                        name(usersItem.getNameCell()).
                        lastName(usersItem.getLastNameCell()).
                        age(Integer.parseInt(usersItem.getAgeCell())).
                        email(usersItem.getEmailCell()).
                        salary(Long.parseLong(usersItem.getSalaryCell())).
                        department(usersItem.getDepartmentCell()).
                        build()
                );
        }
        return usersList;
    }

    public static boolean isUserPresentInList(List<User> userList, User user) {
        return userList.contains(user);
    }
}
