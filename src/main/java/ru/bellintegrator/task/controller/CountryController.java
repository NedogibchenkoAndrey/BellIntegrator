package ru.bellintegrator.task.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.task.service.CountryService;
import ru.bellintegrator.task.view.country.CountryToListView;

import java.util.List;

@RestController
@RequestMapping()
//value = "/api/counter", produces = APPLICATION_JSON_VALUE
public class CountryController {

    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }


    @ApiOperation(value = "Get countries list", httpMethod = "GET")
    @GetMapping("/api/country")
    public List<CountryToListView> findAll() {
        return countryService.findAll();
    }


}
