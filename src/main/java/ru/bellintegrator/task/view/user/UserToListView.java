package ru.bellintegrator.task.view.user;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserToListView {
    private Integer id;
    private String firstName;
    private String secondName;
    private String middleName;
    private String position;
}
