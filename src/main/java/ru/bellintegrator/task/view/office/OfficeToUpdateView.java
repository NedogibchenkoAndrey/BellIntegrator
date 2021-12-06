package ru.bellintegrator.task.view.office;

import lombok.Data;
import ru.bellintegrator.task.response.annotation.Phone;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class OfficeToUpdateView {
    @NotNull(message = "The id field cannot be empty")
    private Integer id;

    @NotNull(message = "The name field cannot be empty")
    @Size(max = 50, message = "The name field cannot be longer than 50")
    private String name;

    @NotNull(message = "The address field cannot be empty")
    @Size(max = 50, message = "The address field cannot be more than 50")
    private String address;

    @Phone(message = "Specify the correct format of the phone number")
    @Size(max= 11, message = "The position field cannot be more than 11")
    private String phone;

    private Boolean isActive;
}
