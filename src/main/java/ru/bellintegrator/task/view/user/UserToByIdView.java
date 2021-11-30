package ru.bellintegrator.task.view.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class UserToByIdView {
    private Integer id;
    private String firstName;
    private String secondName;
    private String middleName;
    private String position;
    private String phone;


    private String docNumber;
    private Date docDate;

    private String docName;
    // Код гражданства пользователя
    private String citizenshipCode;

}
