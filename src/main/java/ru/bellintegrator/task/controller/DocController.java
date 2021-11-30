package ru.bellintegrator.task.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.task.model.Doc;
import ru.bellintegrator.task.service.DocService;
import ru.bellintegrator.task.view.doc.DocView;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/doc", produces = APPLICATION_JSON_VALUE)
public class DocController {

    private final DocService docService;

    @Autowired
    public DocController(DocService docService) {
        this.docService = docService;
    }

    @ApiOperation(value = "Get doc list", nickname = "getDocList", httpMethod = "GET")
    @GetMapping("/list")
    public List<DocView> findAll() {
        return docService.findAll();
    }
}
