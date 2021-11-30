package ru.bellintegrator.task.view.user;

import lombok.Data;

@Data
public class UserFilterView {
    private Integer officeId;
    private String firstName;
    private String middleName;
    private String position;

    //Код типа документа пользователя
    private String docCode;

    // Код гражданства пользователя
    private String citizenshipCode;
}
