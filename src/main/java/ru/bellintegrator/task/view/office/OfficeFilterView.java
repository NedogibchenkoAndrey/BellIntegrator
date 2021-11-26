package ru.bellintegrator.task.view.office;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
public class OfficeFilterView {

    private Integer orgId;
    private String name;
    private String phone;
    @JsonProperty("isActive")
    private Boolean isActive;
}
