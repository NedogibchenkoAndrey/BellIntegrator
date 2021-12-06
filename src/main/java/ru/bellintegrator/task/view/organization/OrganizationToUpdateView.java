package ru.bellintegrator.task.view.organization;

import lombok.Data;
import ru.bellintegrator.task.response.annotation.Phone;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Data
public class OrganizationToUpdateView {
    private Integer id;

    @NotBlank(message = "The name field cannot be empty")
    @Size(max = 50, message = "The name field cannot be more than 50")
    private String name;

    @NotBlank(message = "The fullName field cannot be empty")
    @Size(max = 100, message = "The fullName field cannot be more than 100")
    private String fullName;

    @NotBlank(message = "The inn field cannot be empty")
    @Size(max = 12, message = "The inn field cannot be more than 12")
    @Pattern(regexp = "^[0-9]+$", message = "Enter only numbers in the inn field")
    private String inn;

    @NotBlank(message = "The kpp field cannot be empty")
    @Size(max = 9, min = 9, message = "The kpp field cannot be more than 9")
    @Pattern(regexp = "^[0-9]+$", message = "Enter only numbers in the kpp field")
    private String kpp;

    @NotBlank(message = "The address field cannot be empty")
    @Size(max = 200, message = "The address field cannot be more than 200")
    private String address;

    @Phone(message = "Specify the correct format of the phone number")
    @Size(max= 11, message = "The position field cannot be more than 11")
    private String phone;
    private Boolean isActive;
}
