package ru.bellintegrator.task.view.organization;

import lombok.Data;
import lombok.ToString;

@Data
public class OrganizationToSaveView {
    private String name;
    private String fullName;
    private String inn;
    private String kpp;
    private String address;
    private String phone;
    private Boolean isActive;

}
