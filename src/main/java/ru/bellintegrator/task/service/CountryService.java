package ru.bellintegrator.task.service;

import ru.bellintegrator.task.model.Country;
import ru.bellintegrator.task.view.country.CountryToListView;

import java.util.List;


public interface CountryService {
    List<CountryToListView> findAll();
}
