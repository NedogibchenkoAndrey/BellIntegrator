package ru.bellintegrator.task.view.user;

import lombok.Data;
import ru.bellintegrator.task.response.annotation.Phone;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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

    @Size(max =50, message = "Middle Name minimum of 2 characters, maximum of 50 characters!")
    private String middleName;

    @NotBlank(message = "The position field cannot be empty")
    @Size(max= 50, message = "The position field cannot be more than 20")
    private String position;

    @Phone(message = "Specify the correct format of the phone number")
    @Size(max= 11, message = "The position field cannot be more than 13")
    private String phone;

    @Size(max = 70, message = "The docName field cannot be more than 70")
    private String docName;

    @Size(max = 30, message = "The DocNumber field cannot be more than 30")
    @Pattern(regexp = "^[0-9]+$", message = "Enter only numbers in the docNumber field")
    private String docNumber;

    private Date docDate;

    @Pattern(regexp = "^[0-9]+$", message = "Enter only numbers in the docCode field")
    private String docCode;

    @Pattern(regexp = "^[0-9]+$", message = "Enter only numbers in the citizenshipCode field")
    private String citizenshipCode;

    private Boolean isIdentified;


}
