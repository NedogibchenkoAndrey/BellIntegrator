package ru.bellintegrator.task.view.user;


import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class UserToUpdateView {
    // id user
    @NotNull(message = "The id field cannot be empty!")
    private Integer id;

    // name user
    @NotNull(message = "The firstName field cannot be empty")
    @Size(min = 2, max =50, message = "First Name minimum of 2 characters, maximum of 50 characters!")
    private String firstName;

    //  name user
    @NotNull(message = "The Second Name field cannot be empty")
    @Size(min = 2, max =50, message = "Second Name minimum of 2 characters, maximum of 50 characters!")
    private String secondName;

    //фамилия
    @Size(min = 2, max =50, message = "Middle Name minimum of 2 characters, maximum of 50 characters!")
    private String middleName;
    // number phone
    private String phone;

//     id office from to user
    private Integer officeId;

    // должность пользователя
    private String position;

    // номер документа пользователя
    @Size(max = 30, message = "Поле docNumber не может быть больше 30")
    private String docNumber;

    // дата получения документа
    private Date docDate;

    // Код гражданства пользователя
    private String citizenshipCode;
}
