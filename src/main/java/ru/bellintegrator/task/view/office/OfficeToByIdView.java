package ru.bellintegrator.task.view.office;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OfficeToByIdView {
    private Integer id;
    private String name;
    private String address;
    private String phone;

    @JsonProperty("isActive")
    private Boolean isActive;
}
