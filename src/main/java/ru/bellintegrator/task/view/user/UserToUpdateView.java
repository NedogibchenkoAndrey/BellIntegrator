package ru.bellintegrator.task.view.user;


import lombok.Data;

import java.util.Date;

@Data
public class UserToUpdateView {
    // id user
    private Integer id;

    // name user
    private String firstName;

    // two name user
    private String secondName;

    //фамилия
    private String middleName;
    // number phone
    private String phone;

//     id office from to user
    private Integer officeId;

    // должность пользователя
    private String position;

    // номер документа пользователя
    private String docNumber;

    // дата получения документа
    private Date docDate;

    // Код гражданства пользователя
    private String citizenshipCode;
}
