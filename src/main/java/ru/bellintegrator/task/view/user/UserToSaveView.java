package ru.bellintegrator.task.view.user;

import lombok.Data;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class UserToSaveView {
    private Integer officeId;

    @NotNull(message = "The Second Name field cannot be empty")
    @Size(min = 2, max =50, message = "Second Name minimum of 2 characters, maximum of 50 characters!")
    private String secondName;

    @NotNull(message = "The firstName field cannot be empty")
    @Size(min = 2, max =50, message = "First Name minimum of 2 characters, maximum of 50 characters!")
    private String firstName;

    @Size(min = 2, max =50, message = "Middle Name minimum of 2 characters, maximum of 50 characters!")
    private String middleName;
    private String position;
    private String phone;

    @Size(max = 70, message = "The docName field cannot be more than 70")
    private String docName;

    @Size(max = 30, message = "The DocNumber field cannot be more than 30")
    private String docNumber;
    @Temporal(TemporalType.DATE)
    private Date docDate;
    //Код типа документа пользователя
    private String docCode;
    // Код гражданства пользователя
    private String citizenshipCode;

    private Boolean isIdentified;
}
