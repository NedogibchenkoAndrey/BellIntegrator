package ru.bellintegrator.task.view.organization;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrganizationToListView {
    private Integer id;
    private String name;
    @JsonProperty("isActive")
    private Boolean isActive;
}
