package ru.bellintegrator.task.view.organization;


import lombok.Data;

@Data
public class OrganizationToByIdView {
    private Integer id;
    private String name;
    private String fullName;
    private String inn;
    private String kpp;
    private String address;
    private String phone;
    private Boolean isActive;
}
