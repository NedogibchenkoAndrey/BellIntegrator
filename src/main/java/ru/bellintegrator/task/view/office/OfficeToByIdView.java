package ru.bellintegrator.task.view.office;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OfficeToByIdView {
    private Integer id;
    private String name;
    private String address;
    private String phone;

    @JsonProperty("isActive")
    private Boolean isActive;
}
