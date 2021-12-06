package ru.bellintegrator.task.view.user;


import lombok.Data;

@Data
public class UserToListView {
    private Integer id;
    private String firstName;
    private String secondName;
    private String middleName;
    private String position;
}
