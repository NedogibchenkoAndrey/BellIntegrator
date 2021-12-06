package ru.bellintegrator.task.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.task.service.OfficeService;
import ru.bellintegrator.task.view.office.OfficeFilterView;
import ru.bellintegrator.task.view.office.OfficeToListView;
import ru.bellintegrator.task.view.office.OfficeToSaveView;
import ru.bellintegrator.task.view.office.OfficeToUpdateView;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/office", produces = APPLICATION_JSON_VALUE)
public class OfficeController {

    private final OfficeService officeService;

    @Autowired
    public OfficeController(OfficeService officeService) {
        this.officeService = officeService;
    }

    @ApiOperation(value = "Get office list", httpMethod = "POST")
    @PostMapping("/list")
    public List<OfficeToListView> findAll(@Valid @RequestBody OfficeFilterView filterView){
        return officeService.findAll(filterView);
    }
    @ApiOperation(value = "Get office by id", httpMethod = "GET")
    @GetMapping("/{id}")
    public OfficeToUpdateView findById(@PathVariable Integer id) {
        return officeService.findById(id);
    }

    @ApiOperation(value = "Get office update", httpMethod = "POST")
    @PostMapping("/update")
    public void update(@Valid @RequestBody OfficeToUpdateView officeToUpdateView) {
        officeService.update(officeToUpdateView);
    }

    @ApiOperation(value = "Get office Save",httpMethod = "POST")
    @PostMapping("/save")
    public void add(@Valid @RequestBody OfficeToSaveView officeToSaveView) {
        officeService.save(officeToSaveView);
    }
}
