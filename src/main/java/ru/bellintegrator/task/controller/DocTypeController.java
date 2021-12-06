package ru.bellintegrator.task.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.task.service.DocTypeService;
import ru.bellintegrator.task.view.doctype.DocTypeView;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/doctype", produces = APPLICATION_JSON_VALUE)
public class DocTypeController {

    private final DocTypeService docService;

    @Autowired
    public DocTypeController(DocTypeService docService) {
        this.docService = docService;
    }

    @ApiOperation(value = "Get doc list", httpMethod = "GET")
    @GetMapping("/list")
    public List<DocTypeView> findAll() {
        return docService.findAll();
    }
}
