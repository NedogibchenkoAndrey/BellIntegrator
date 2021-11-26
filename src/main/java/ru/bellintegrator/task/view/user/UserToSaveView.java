package ru.bellintegrator.task.view.user;

import lombok.Data;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
public class UserToSaveView {
    private Integer officeId;

    private String secondName;
    private String firstName;
    private String middleName;
    private String position;
    private String phone;

    private String docName;
    private String docNumber;
    @Temporal(TemporalType.DATE)
    private Date docDate;
    //Код типа документа пользователя
    private String docCode;
    // Код гражданства пользователя
    private String citizenshipCode;

    private Boolean isIdentified;
}
