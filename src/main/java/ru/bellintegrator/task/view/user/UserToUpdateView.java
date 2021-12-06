package ru.bellintegrator.task.view.user;


import lombok.Data;
import ru.bellintegrator.task.response.annotation.Phone;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class UserToUpdateView {

    @NotNull(message = "The id field cannot be empty!")
    private Integer id;

    @NotNull(message = "The firstName field cannot be empty")
    @Size(min = 2, max =50, message = "First Name minimum of 2 characters, maximum of 50 characters!")
    private String firstName;

    @NotNull(message = "The Second Name field cannot be empty")
    @Size(min = 2, max =50, message = "Second Name minimum of 2 characters, maximum of 50 characters!")
    private String secondName;

    @Size(min = 2, max =50, message = "Middle Name minimum of 2 characters, maximum of 50 characters!")
    private String middleName;

    @Phone(message = "Specify the correct format of the phone number")
    @Size(max= 11, message = "The position field cannot be more than 13")
    private String phone;

    private Integer officeId;

    @NotBlank(message = "The position field cannot be empty")
    @Size(max= 50, message = "The position field cannot be more than 20")
    private String position;

    @Size(max = 30, message = "The DocNumber field cannot be more than 30")
    @Pattern(regexp = "^[0-9]+$", message = "Enter only numbers in the docNumber field")
    private String docNumber;

    private Date docDate;

    @Pattern(regexp = "^[0-9]+$", message = "Enter only numbers in the citizenshipCode field")
    private String citizenshipCode;
}
