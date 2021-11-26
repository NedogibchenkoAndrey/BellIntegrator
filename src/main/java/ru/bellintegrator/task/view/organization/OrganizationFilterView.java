package ru.bellintegrator.task.view.organization;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
public class OrganizationFilterView {
    private String name;
    private String inn;
    @JsonProperty("isActive")
    private Boolean isActive;
}
