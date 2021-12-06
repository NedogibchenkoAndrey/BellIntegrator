package ru.bellintegrator.task.view.user;

import lombok.Data;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class UserFilterView {

    private Integer officeId;

    @Size(max=50, message = "The office firstName field cannot be empty")
    private String firstName;

    @Size(max=50, message = "The office middleName field cannot be empty")
    private String middleName;

    @Size(max=50, message = "The office position field cannot be empty")
    private String position;

    @Pattern(regexp = "^[0-9]+$", message = "Enter only numbers in the docCode field")
    private String docCode;

    @Pattern(regexp = "^[0-9]+$", message = "Enter only numbers in the citizenshipCode field")
    private String citizenshipCode;
}
