package ru.bellintegrator.task.view.office;

import lombok.Data;
import lombok.ToString;

@Data
public class OfficeToUpdateView {
    private Integer id;
    private String name;
    private String address;
    private String phone;
    private Boolean isActive;
}
