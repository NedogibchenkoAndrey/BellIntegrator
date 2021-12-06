package ru.bellintegrator.task.view.organization;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class OrganizationFilterView {
    @NotBlank(message = "The name field cannot be empty")
    @Size(max = 50, message = "The name field cannot be more than 50")
    private String name;

    @Size(max = 12, message = "The inn field cannot be more than 12")
    @Pattern(regexp = "^[0-9]+$", message = "Enter only numbers in the kpp field")
    private String inn;
    private Boolean isActive;
}
