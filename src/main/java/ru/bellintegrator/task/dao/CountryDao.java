package ru.bellintegrator.task.dao;

import org.springframework.data.repository.CrudRepository;
import ru.bellintegrator.task.model.Country;

import java.util.List;

public interface CountryDao extends CrudRepository<Country, Integer> {
    Country findCountryByCode(String code);
}
