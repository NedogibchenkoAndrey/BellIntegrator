package ru.bellintegrator.task.view.organization;

import lombok.Data;

@Data
public class OrganizationToListView {
    private Integer id;
    private String name;
    private Boolean isActive;
}
