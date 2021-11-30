package ru.bellintegrator.task.view.office;

import lombok.Data;
import lombok.ToString;

@Data
public class OfficeToSaveView {
    private Integer orgId;
    private String name;
    private String address;
    private String phone;
    private Boolean isActive;
}
