package ru.bellintegrator.task.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.task.service.OrganizationService;
import ru.bellintegrator.task.view.organization.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/org", produces = APPLICATION_JSON_VALUE)
public class OrganizationController {

    private final OrganizationService organizationService;

    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @ApiOperation(value = "Get organization list", httpMethod = "POST")
    @PostMapping("/list")
    public List<OrganizationToListView> findAll(@Valid @RequestBody OrganizationFilterView orgFilter) {
        return organizationService.findAll(orgFilter);
    }

    @ApiOperation(value = "Get organization by id", httpMethod = "GET")
    @GetMapping("/{id}")
    public OrganizationToByIdView findById(@PathVariable Integer id) {
        return organizationService.findById(id);
    }

    @ApiOperation(value = "Save organization", httpMethod = "POST")
    @PostMapping("/save")
    public void add(@Valid @RequestBody OrganizationToSaveView orgToSaveView) {
        organizationService.save(orgToSaveView);
    }

    @ApiOperation(value = "Update organization", httpMethod = "POST")
    @PostMapping("/update")
    public void update(@Valid @RequestBody OrganizationToUpdateView orgToUpdateView) {
        organizationService.update(orgToUpdateView);
    }

}
